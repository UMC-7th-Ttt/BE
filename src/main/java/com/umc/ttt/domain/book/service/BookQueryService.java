package com.umc.ttt.domain.book.service;

import com.umc.ttt.domain.book.dto.BookResponseDTO;

public interface BookQueryService {
    BookResponseDTO.SearchBookResultDTO searchBooks(String keyword, long cursor, int limit);

    BookResponseDTO.SuggestBooksResultDTO suggestBooks(String categoryName);
}