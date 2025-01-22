package com.umc.ttt.domain.scrap.service;

import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.scrap.dto.ScrapRequestDTO;
import com.umc.ttt.domain.scrap.dto.ScrapResponseDTO;

public interface ScrapCommandService {
    ScrapResponseDTO.PlaceScrapDTO addPlaceScrap(Long placeId, String folder, Member member);

    ScrapResponseDTO.BookScrapDTO addBookScrap(Long bookId, String folder, Member member);

    ScrapResponseDTO.ScrapFolderDTO createScrapFolder(String folder, Member member);

    Long deleteScrapFolder(Long folderId, Member member);

    ScrapResponseDTO.PlaceScrapDTO removePlaceScrap(Long placeId, Member member);

    ScrapResponseDTO.BookScrapDTO removeBookScrap(Long bookId, Member member);

    void removeScraps(ScrapRequestDTO.ScrapRemoveRequestDTO scrapRemoveRequestDTO);
}
