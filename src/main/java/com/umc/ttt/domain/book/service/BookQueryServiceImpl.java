package com.umc.ttt.domain.book.service;

import com.umc.ttt.domain.book.converter.BookConverter;
import com.umc.ttt.domain.book.dto.BookResponseDTO;
import com.umc.ttt.domain.book.entity.Book;
import com.umc.ttt.domain.book.repository.BookRepository;
import com.umc.ttt.global.apiPayload.code.status.ErrorStatus;
import com.umc.ttt.global.apiPayload.exception.handler.BookHandler;
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
        Pageable pageable = PageRequest.of(0, limit + 1);
        List<Book> books = bookRepository.findBooksByKeyword(keyword, cursor, pageable);

        if (books.isEmpty() && cursor != 0) {
            throw new BookHandler(ErrorStatus.PAGE_NOT_FOUND);
        }

        long nextCursor = books.isEmpty() ? null : books.get(books.size() - 1).getId();
        boolean hasNext = books.size() > limit;
        List<Book> paginatedBooks = hasNext ? books.subList(0, limit) : books;

        return BookConverter.toSearchBooksResultDTO(paginatedBooks, nextCursor, limit, hasNext);
    }
}
