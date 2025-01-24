package com.umc.ttt.domain.scrap.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ScrapRequestDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScrapRemoveRequestDTO {
        @Valid
        @NotEmpty(message = "삭제할 스크랩 id 목록은 필수입니다.")
        private List<ScrapItemDTO> scraps;

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ScrapItemDTO {
            @NotNull(message = "스크랩 id는 필수입니다.")
            private Long scrapId;
            @NotNull(message = "스크랩 타입은 필수입니다.")
            @Pattern(regexp = "PLACE|BOOK|place|book", message = "스크랩 타입은 'PLACE' 또는 'BOOK'만 가능합니다.")
            private String type;
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScrapFolderMoveRequestDTO {
        @NotNull(message = "이동할 폴더 id는 필수입니다.")
        private Long newFolderId;

        @Valid
        @NotEmpty(message = "이동할 스크랩 id 목록은 필수입니다.")
        private List<ScrapItemDTO> scraps;

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ScrapItemDTO {
            @NotNull(message = "스크랩 id는 필수입니다.")
            private Long scrapId;
            @NotNull(message = "스크랩 타입은 필수입니다.")
            @Pattern(regexp = "PLACE|BOOK|place|book", message = "스크랩 타입은 'PLACE' 또는 'BOOK'만 가능합니다.")
            private String type;
        }
    }
}
