package com.umc.ttt.domain.scrap.converter;

import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.place.entity.Place;
import com.umc.ttt.domain.scrap.dto.ScrapResponseDTO;

public class ScrapConverter {

    public static ScrapResponseDTO.PlaceScrapDTO toPlaceScrapDTO(Place place, Member member, boolean isScraped) {
        return ScrapResponseDTO.PlaceScrapDTO.builder()
                .memberId(member.getId())
                .placeId(place.getId())
                .isScraped(isScraped)
                .build();
    }

}
