package com.umc.ttt.domain.place.dto;

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

}
