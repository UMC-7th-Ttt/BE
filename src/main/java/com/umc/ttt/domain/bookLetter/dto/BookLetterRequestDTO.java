package com.umc.ttt.domain.bookLetter.dto;

import lombok.Getter;

import java.util.List;

public class BookLetterRequestDTO {
    @Getter
    public static class CRDto {
        List<Long> booksId;
        String title;
        String subtitle;
        String editor;
        String content;
        String coverImg;
    }
}
