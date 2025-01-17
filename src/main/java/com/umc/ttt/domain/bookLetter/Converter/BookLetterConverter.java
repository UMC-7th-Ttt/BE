package com.umc.ttt.domain.bookLetter.Converter;

import com.umc.ttt.domain.book.entity.Book;
import com.umc.ttt.domain.bookLetter.dto.BookLetterRequestDTO;
import com.umc.ttt.domain.bookLetter.dto.BookLetterResponseDTO;
import com.umc.ttt.domain.bookLetter.entity.BookLetter;

import java.util.List;

public class BookLetterConverter {
    public static BookLetterResponseDTO.CRDResultDTO toCRResultDTO(BookLetter bookLetter) {
        return BookLetterResponseDTO.CRDResultDTO.builder()
                .bookLetterId(bookLetter.getId())
                .build();
    }

    public static BookLetter toBookLetter(BookLetterRequestDTO.CRDto request, List<Book> books) {
        return BookLetter.builder()
                .books(books)
                .title(request.getTitle())
                .subtitle(request.getSubtitle())
                .editor(request.getEditor())
                .content(request.getContent())
                .coverImg(request.getCoverImg())
                .build();
    }
}
