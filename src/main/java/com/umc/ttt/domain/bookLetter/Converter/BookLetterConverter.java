package com.umc.ttt.domain.bookLetter.Converter;

import com.umc.ttt.domain.book.entity.Book;
import com.umc.ttt.domain.bookLetter.dto.BookLetterRequestDTO;
import com.umc.ttt.domain.bookLetter.dto.BookLetterResponseDTO;
import com.umc.ttt.domain.bookLetter.entity.BookLetter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

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

    // 북레터 리스트 조회
    public static BookLetterResponseDTO.BookLetterPreViewDTO bookLetterPreViewDTO(BookLetter bookLetter) {
        return BookLetterResponseDTO.BookLetterPreViewDTO.builder()
                .bookLetterId(bookLetter.getId())
                .title(bookLetter.getTitle())
                .editor(bookLetter.getEditor())
                .build();
    }

    public static BookLetterResponseDTO.BookLetterListDTO bookLetterListDTO(Page<BookLetter> bookLetterList) {
        List<BookLetterResponseDTO.BookLetterPreViewDTO> bookLetterPreviewDTOList = bookLetterList.stream()
                .map(BookLetterConverter::bookLetterPreViewDTO).collect(Collectors.toList());

        return BookLetterResponseDTO.BookLetterListDTO.builder()
                .isLastPage(bookLetterList.isLast())
                .isFirstPage(bookLetterList.isFirst())
                .totalPage(bookLetterList.getTotalPages())
                .totalElements(bookLetterList.getTotalElements())
                .listSize(bookLetterPreviewDTOList.size())
                .bookLetterList(bookLetterPreviewDTOList)
                .build();
    }
}
