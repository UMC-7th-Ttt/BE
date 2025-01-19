package com.umc.ttt.domain.place.service;

import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.place.dto.PlaceResponseDTO;

public interface PlaceQueryService {
    PlaceResponseDTO.PlaceDTO getPlace(Long placeId, Member member);

    PlaceResponseDTO.PlaceListDTO getPlaceList(Double lat, Double lon, String sort, Long cursor, int limit, Member member);
}
