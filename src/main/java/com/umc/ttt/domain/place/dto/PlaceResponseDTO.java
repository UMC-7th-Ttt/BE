package com.umc.ttt.domain.place.dto;

import com.umc.ttt.domain.place.entity.enums.PlaceCategory;
import lombok.Builder;
import lombok.Getter;

public class PlaceResponseDTO {

    @Builder
    @Getter
    public static class CurationDTO {
        private Long memberId;
        private String placeId;
        private String curationTitle;
        private String curationContent;
    }

    @Builder
    @Getter
    public static class PlaceDTO {
        private Long placeId;
        private String title;
        private PlaceCategory category;
        private String address;
        private String holiday;
        private String weekdaysBusiness;
        private String sunBusiness;
        private String phone;
        private boolean hasParking;
        private boolean hasCafe;
        private boolean hasIndiePub;
        private boolean hasBookClub;
        private boolean hasSpaceRental;
        private String image;
        private double userRating;  // 같은 취향 유저들의 평점
        private double totalRating;  // 전체 평점
        private String curationTitle;
        private String curationContent;
        private boolean isAdmin;
    }

}
