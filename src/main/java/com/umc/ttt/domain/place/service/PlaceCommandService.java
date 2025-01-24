package com.umc.ttt.domain.place.service;

import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.place.dto.PlaceRequestDTO;
import com.umc.ttt.domain.place.dto.PlaceResponseDTO;

public interface PlaceCommandService {
    PlaceResponseDTO.CurationDTO updateCuration(Long placeId, PlaceRequestDTO.CurationDTO curationDTO, Member member);
}
