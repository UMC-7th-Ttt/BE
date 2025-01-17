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
}
