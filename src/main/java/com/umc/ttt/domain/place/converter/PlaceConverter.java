package com.umc.ttt.domain.place.converter;

import com.umc.ttt.domain.place.dto.PlaceResponseDTO;
import com.umc.ttt.domain.place.entity.Place;

public class PlaceConverter {

    public static PlaceResponseDTO.CurationDTO curationPreviewDTO(Place place, Long memberId) {
        return PlaceResponseDTO.CurationDTO.builder()
                .memberId(memberId)
                .placeId(String.valueOf(place.getId()))
                .curationTitle(place.getCurationTitle())
                .curationContent(place.getCurationContent())
                .build();
    }

}
