package com.umc.ttt.domain.scrap.service;

import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.scrap.dto.ScrapResponseDTO;

public interface ScrapQueryService {
    ScrapResponseDTO.ScrapFolderListDTO getScrapFolders(Member member);

    ScrapResponseDTO.ScrapListDTO getScrapList(Long folderId, Long bookCursor, Long placeCursor, int limit, Member member);
}
