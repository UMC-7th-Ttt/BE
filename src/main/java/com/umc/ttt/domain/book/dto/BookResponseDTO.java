package com.umc.ttt.domain.book.dto;

import lombok.*;

import java.util.List;

public class BookResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchBookResultDTO {
        private List<BookInfoDTO> books;
        private Long cursor;
        private int limit;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookInfoDTO {
        private Long id;
        private String cover;
        private String title;
        private String author;
        private String category;
        private String publisher;
    }
}