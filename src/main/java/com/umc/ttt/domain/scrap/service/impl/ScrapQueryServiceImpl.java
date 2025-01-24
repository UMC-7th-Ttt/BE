package com.umc.ttt.domain.scrap.service.impl;

import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.scrap.converter.ScrapConverter;
import com.umc.ttt.domain.scrap.dto.ScrapResponseDTO;
import com.umc.ttt.domain.scrap.entity.BookScrap;
import com.umc.ttt.domain.scrap.entity.PlaceScrap;
import com.umc.ttt.domain.scrap.entity.ScrapFolder;
import com.umc.ttt.domain.scrap.repository.BookScrapRepository;
import com.umc.ttt.domain.scrap.repository.PlaceScrapRepository;
import com.umc.ttt.domain.scrap.repository.ScrapFolderRepository;
import com.umc.ttt.domain.scrap.service.ScrapQueryService;
import com.umc.ttt.global.apiPayload.code.status.ErrorStatus;
import com.umc.ttt.global.apiPayload.exception.handler.ScrapHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScrapQueryServiceImpl implements ScrapQueryService {

    private final ScrapFolderRepository scrapFolderRepository;
    private final PlaceScrapRepository placeScrapRepository;
    private final BookScrapRepository bookScrapRepository;

    @Override
    public ScrapResponseDTO.ScrapFolderListDTO getScrapFolders(Member member) {
        List<ScrapFolder> scrapFolders = scrapFolderRepository.findAllByMember(member);
        return ScrapConverter.toScrapFolderDTOList(scrapFolders);
    }

    @Override
    public ScrapResponseDTO.ScrapListDTO getScrapList(Long folderId, Long bookCursor, Long placeCursor, int limit, Member member) {
        ScrapFolder scrapFolder = scrapFolderRepository.findByIdAndMember(folderId, member)
                .orElseThrow(() -> new ScrapHandler(ErrorStatus.FOLDER_NOT_FOUND));

        Pageable pageable = PageRequest.of(0, limit + 1, Sort.by(Sort.Order.desc("id")));

        // 커서 기반으로 데이터 가져오기
        List<BookScrap> bookScraps = null;
        List<PlaceScrap> placeScraps = null;

        if (bookCursor != null) {
            bookScraps = bookScrapRepository.findBookScrapsWithCursor(scrapFolder.getId(), bookCursor, pageable);
        }

        if (placeCursor != null) {
            placeScraps = placeScrapRepository.findPlaceScrapsWithCursor(scrapFolder.getId(), placeCursor, pageable);
        }

        // dto 변환 후 하나의 리스트로 합치기
        List<ScrapResponseDTO.ScrapDTO> allScraps = new ArrayList<>();
        if (bookScraps != null) {
            allScraps.addAll(bookScraps.stream().map(ScrapConverter::toScrapDTO).toList());
        }
        if (placeScraps != null) {
            allScraps.addAll(placeScraps.stream().map(ScrapConverter::toScrapDTO).toList());
        }

        // 최신 순 정렬
        allScraps.sort(Comparator.comparing(ScrapResponseDTO.ScrapDTO::getCreatedAt).reversed());

        int end = Math.min(limit, allScraps.size());
        List<ScrapResponseDTO.ScrapDTO> paginatedScraps = allScraps.subList(0, end);

        // paginatedScraps에서 각 마지막 스크랩 id를 nextCursor로 설정
        Long nextBookCursor = null;
        Long nextPlaceCursor = null;
        long paginatedBookCount = 0;

        if (bookScraps != null) {
            paginatedBookCount = paginatedScraps.stream()
                    .filter(scrap -> scrap.getType().equals("BOOK"))
                    .count();

            if (bookScraps.size() > paginatedBookCount) {
                nextBookCursor = paginatedScraps.stream()
                        .filter(scrap -> scrap.getType().equals("BOOK"))
                        .map(ScrapResponseDTO.ScrapDTO::getId)
                        .reduce((first, second) -> second)
                        .orElse(null);
            }
        }

        if (placeScraps != null) {
            long paginatedPlaceCount = paginatedScraps.size() - paginatedBookCount;

            if (placeScraps.size() > paginatedPlaceCount) {
                nextPlaceCursor = paginatedScraps.stream()
                        .filter(scrap -> scrap.getType().equals("PLACE"))
                        .map(ScrapResponseDTO.ScrapDTO::getId)
                        .reduce((first, second) -> second)
                        .orElse(null);
            }
        }

        boolean hasNext = (nextBookCursor != null || nextPlaceCursor != null);

        return ScrapConverter.toScrapListDTO(paginatedScraps, nextBookCursor, nextPlaceCursor, limit, hasNext);
    }

}
