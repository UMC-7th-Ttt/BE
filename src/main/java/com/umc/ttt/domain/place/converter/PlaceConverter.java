package com.umc.ttt.domain.place.converter;

import com.umc.ttt.domain.member.entity.Member;
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

    public static PlaceResponseDTO.PlaceDTO toPlaceDTO(Place place, Member member, boolean isAdmin) {
        return PlaceResponseDTO.PlaceDTO.builder()
                .placeId(place.getId())
                .title(place.getTitle())
                .category(place.getCategory())
                .address(place.getAddress())
                .holiday(place.getHoliday())
                .weekdaysBusiness(place.getWeekdaysBusiness())
                .sunBusiness(place.getSunBusiness())
                .phone(place.getPhone())
                .hasParking(place.isHasParking())
                .hasCafe(place.isHasCafe())
                .hasIndiePub(place.isHasIndiePub())
                .hasBookClub(place.isHasBookClub())
                .hasSpaceRental(place.isHasSpaceRental())
                .image(place.getImage())
                .userRating(getUserRating(place, member))   // 같은 취향 유저들의 평점
                .totalRating(place.getRating()) // 전체 평점
                .curationTitle(place.getCurationTitle())
                .curationContent(place.getCurationContent())
                .isAdmin(isAdmin)
                .build();
    }

    private static double getUserRating(Place place, Member member) {
        // TODO: 사용자 평점 계산 로직 - 추후 구현
        return 0.0;
    }

}
