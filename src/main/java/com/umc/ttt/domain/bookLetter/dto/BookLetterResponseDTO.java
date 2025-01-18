package com.umc.ttt.domain.bookLetter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class BookLetterResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CRDResultDTO {
        Long bookLetterId;
    }

    // 북레터 리스트
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookLetterListDTO{
        List<BookLetterPreViewDTO> bookLetterList;
        Integer totalPage;
        Integer listSize;
        Long totalElements;
        Boolean isFirstPage;
        Boolean isLastPage;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookLetterPreViewDTO {
        Long bookLetterId;
        String title;
        String editor;
    }

    // 북레터 상세 페이지
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookLetterDTO {
        String title;
        String subtitle;
        String editor;
        Boolean isWriter;
        String content;
        String coverImg;
        List<BookDTO> bookList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookDTO{
        Long bookId;
        String title;
        String author;
        String cover;
        String publisher;
        Integer itemPage;
        String categoryName;
        Boolean hasEbook;
        String description;
    }
}
