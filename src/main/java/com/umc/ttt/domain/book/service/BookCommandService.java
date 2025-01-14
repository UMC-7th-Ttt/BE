package com.umc.ttt.domain.book.service;

import com.umc.ttt.domain.book.dto.BookResponseDTO;

import java.util.List;

public interface BookCommandService {
    public List<BookResponseDTO.Item> fetchBooks();
}
