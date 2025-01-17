package com.umc.ttt.domain.place.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PlaceRequestDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CurationDTO {
        @NotNull(message = "큐레이션 제목은 필수입니다.")
        private String curationTitle;

        @NotNull(message = "큐레이션 내용은 필수입니다.")
        private String curationContent;
    }

}
