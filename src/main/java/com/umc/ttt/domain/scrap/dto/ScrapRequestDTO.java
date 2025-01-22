package com.umc.ttt.domain.scrap.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ScrapRequestDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScrapRemoveRequestDTO {
        private List<ScrapItemDTO> scraps;

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ScrapItemDTO {
            @NotNull(message = "스크랩 id는 필수입니다.")
            private Long scrapId;
            @NotNull(message = "스크랩 타입(PLACE, BOOK)은 필수입니다.")
            private String type;
        }
    }
}
