package com.umc.ttt.domain.book.service;

import com.umc.ttt.domain.book.converter.BookConverter;
import com.umc.ttt.domain.book.dto.BookResponseDTO;
import com.umc.ttt.domain.book.entity.Book;
import com.umc.ttt.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookQueryServiceImpl implements BookQueryService {

    private final BookRepository bookRepository;

    @Override
    public BookResponseDTO.SearchBookResultDTO searchBooks(String keyword, long cursor, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Book> books = bookRepository.findBooksByKeyword(keyword, cursor, pageable);

        return BookConverter.toSearchBooksResultDTO(books, cursor, limit);
    }
}
