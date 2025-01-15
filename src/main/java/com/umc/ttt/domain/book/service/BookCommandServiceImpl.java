package com.umc.ttt.domain.book.service;

import com.umc.ttt.domain.book.converter.BookConverter;
import com.umc.ttt.domain.book.dto.BookResponseDTO;
import com.umc.ttt.domain.book.entity.Book;
import com.umc.ttt.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookCommandServiceImpl implements BookCommandService {

    private final BookRepository bookRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${aladin.api.base-url}")
    private String baseUrl;

    @Value("${aladin.api.query-params}")
    private String queryParams;

    @Value("${aladin.api.ttbkey}")
    private String ttbkey;

    @Override
    @Transactional
    public List<BookResponseDTO.Item> fetchBooks() {
        if (ttbkey == null) {
            throw new RuntimeException("환경변수 ALADIN_TTBKEY가 설정되어 있지 않습니다.");
        }

        List<BookResponseDTO.Item> allItems = new ArrayList<>();
        int maxResults = 50;
        int startPage = 1;

        // CategoryId 목록 (판타지, 미스터리, 고전, 성장, 심리학, 비즈니스, 역사, 논리, 로맨스, 감성, 힐링, 종교학, 문화, 철학, 예술)
        List<Integer> categoryIds = List.of(50928, 152930, 103639, 336, 51395, 2172, 74, 51412, 50935, 50917, 70236, 1237, 2177, 51387, 517);

        for (int categoryId : categoryIds) {
            String apiUrl = String.format(baseUrl + queryParams + "&CategoryId=%d", ttbkey, maxResults, startPage, categoryId);

            BookResponseDTO response = restTemplate.getForObject(apiUrl, BookResponseDTO.class);

            if (response == null || response.getItem() == null || response.getItem().isEmpty()) {
                System.out.println("CategoryId " + categoryId + "에 대한 데이터가 없습니다.");
                continue;
            }

            allItems.addAll(response.getItem());

            List<Book> books = response.getItem().stream()
                    .map(BookConverter::toEntity)
                    .collect(Collectors.toList());
            bookRepository.saveAll(books);
        }

        return allItems;
    }
}
