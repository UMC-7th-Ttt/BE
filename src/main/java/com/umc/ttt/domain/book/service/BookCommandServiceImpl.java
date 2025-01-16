package com.umc.ttt.domain.book.service;

import com.umc.ttt.domain.book.converter.BookConverter;
import com.umc.ttt.domain.book.dto.BookFetchDTO;
import com.umc.ttt.domain.book.entity.Book;
import com.umc.ttt.domain.book.entity.BookCategory;
import com.umc.ttt.domain.book.repository.BookCategoryRepository;
import com.umc.ttt.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCommandServiceImpl implements BookCommandService {

    private final BookRepository bookRepository;
    private final BookCategoryRepository bookCategoryRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${aladin.api.base-url}")
    private String baseUrl;

    @Value("${aladin.api.query-params}")
    private String queryParams;

    @Value("${aladin.api.item-lookup-url}")
    private String itemLookupUrl;

    @Value("${aladin.api.item-query-params}")
    private String itemQueryParams;

    @Value("${aladin.api.ttbkey}")
    private String ttbkey;

    @Override
    @Transactional
    public void fetchBooks() {
        if (ttbkey == null) {
            throw new RuntimeException("환경변수 ALADIN_TTBKEY가 설정되어 있지 않습니다.");
        }

        List<BookFetchDTO.Item> allItems = new ArrayList<>();
        int maxResults = 50;
        int startPage = 1;

        // CategoryId 목록
        // 과학, 여행에세이, 외국문학, 시, 장르소설, 자기계발, 인문, 소설
        // 판타지, 미스터리, 고전, 성장, 심리학, 비즈니스, 역사, 논리, 로맨스, 감성, 힐링, 종교학, 문화, 철학, 예술
        List<Integer> categoryIds = List.of(987, 51377, 50955, 50940, 112011, 336, 656, 1,
                50928, 50926, 103639, 51235, 51395, 2172, 74, 51412, 50935, 50917, 70236, 1237, 2177, 51387, 517);

        for (int i = 0; i < categoryIds.size(); i++) {
            int categoryId = categoryIds.get(i);
            int bookCategoryId = i + 1;

            BookCategory bookCategory = bookCategoryRepository.findById((long) bookCategoryId)
                    .orElseThrow(() -> new RuntimeException("BookCategoryId " + bookCategoryId + "를 찾을 수 없습니다."));

            String apiUrl = String.format(baseUrl + queryParams + "&CategoryId=%d", ttbkey, maxResults, startPage, categoryId);

            BookFetchDTO response = restTemplate.getForObject(apiUrl, BookFetchDTO.class);

            if (response == null || response.getItem() == null || response.getItem().isEmpty()) {
                System.out.println("CategoryId " + categoryId + "에 대한 데이터가 없습니다.");
                continue;
            }

            for (BookFetchDTO.Item item : response.getItem()) {
                if (bookRepository.findByIsbn(item.getIsbn()).isPresent()) {
                    continue;
                }

                String lookupUrl = String.format(itemLookupUrl + itemQueryParams, ttbkey, item.getIsbn());
                BookFetchDTO lookupResponse = restTemplate.getForObject(lookupUrl, BookFetchDTO.class);

                if (lookupResponse != null && lookupResponse.getItem() != null && !lookupResponse.getItem().isEmpty()) {
                    BookFetchDTO.Item lookupItem = lookupResponse.getItem().get(0);

                    item.setItemPage(lookupItem.getItemPage());
                    item.setHasEbook(lookupItem.isHasEbook());
                }

                allItems.add(item);

                Book bookEntity = BookConverter.toEntity(item, bookCategory);
                bookRepository.save(bookEntity);
            }
        }
    }
}
