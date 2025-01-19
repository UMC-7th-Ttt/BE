package com.umc.ttt.domain.scrap.service;

import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.scrap.dto.ScrapResponseDTO;

import java.util.List;

public interface ScrapQueryService {
    List<ScrapResponseDTO.ScrapFolderDTO> getScrapFolders(Member member);

    ScrapResponseDTO.ScrapListDTO getScrapList(Long folderId, Long bookCursor, Long placeCursor, int limit, Member member);
}
