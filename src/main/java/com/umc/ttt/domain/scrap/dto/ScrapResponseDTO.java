package com.umc.ttt.domain.scrap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ScrapResponseDTO {

    @Builder
    @Getter
    public static class PlaceScrapDTO {
        private Long memberId;
        private Long placeId;
        private boolean isScraped;
    }

    @Builder
    @Getter
    public static class BookScrapDTO {
        private Long memberId;
        private Long bookId;
        private boolean isScraped;
    }

    @Builder
    @Getter
    @RequiredArgsConstructor
    @AllArgsConstructor
    public static class ScrapFolderDTO {
        private Long folderId;
        private String name;
        private List<String> images;
    }

    @Builder
    @Getter
    @RequiredArgsConstructor
    @AllArgsConstructor
    public static class ScrapFolderListDTO {
        private int folderCount;
        private List<ScrapFolderDTO> folders;
    }

    @Builder
    @Getter
    public static class ScrapDTO {
        private Long id;
        private String title;
        private String authorOrAddress;
        private String image;
        private String type;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    public static class ScrapListDTO {
        private List<ScrapDTO> scraps;
        private Long nextBookCursor;
        private Long nextPlaceCursor;
        private int limit;
        private boolean hasNext;
    }
}
