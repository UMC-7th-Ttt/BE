package com.umc.ttt.domain.scrap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ScrapResponseDTO {

    @Builder
    @Getter
    public static class PlaceScrapDTO {
        private Long memberId;
        private Long placeId;
        private boolean isScraped;
    }
}
