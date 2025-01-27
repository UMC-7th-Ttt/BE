package com.umc.ttt.domain.book.converter;

import com.umc.ttt.domain.book.dto.BookFetchDTO;
import com.umc.ttt.domain.book.dto.BookResponseDTO;
import com.umc.ttt.domain.book.entity.Book;
import com.umc.ttt.domain.book.entity.BookCategory;

import java.util.List;
import java.util.stream.Collectors;

public class BookConverter {

    public static Book toEntity(BookFetchDTO.Item item, BookCategory bookCategory) {
        return Book.builder()
                .title(item.getTitle())
                .author(item.getAuthor())
                .isbn(item.getIsbn())
                .cover(item.getCover())
                .description(item.getDescription())
                .publisher(item.getPublisher())
                .bestRank(item.getBestRank())
                .link(item.getLink())
                .itemPage(item.getItemPage())
                .hasEbook(item.getHasEbook())
                .bookCategory(bookCategory)
                .build();
    }

    public static BookResponseDTO.SearchBookResultDTO toSearchBookResultDTO(List<Book> books, long nextCursor, int limit, boolean hasNext) {
        List<BookResponseDTO.BookInfoDTO> bookInfoList = books.stream()
                .map(book -> toBookInfoDTO(book))
                .collect(Collectors.toList());

        return BookResponseDTO.SearchBookResultDTO.builder()
                .books(bookInfoList)
                .nextCursor(nextCursor)
                .limit(limit)
                .hasNext(hasNext)
                .build();
    }

    public static BookResponseDTO.SuggestBooksResultDTO toSuggestBooksResultDTO(List<Book> books) {
        List<BookResponseDTO.BookInfoDTO> bookInfoList = books.stream()
                .map(book -> toBookInfoDTO(book))
                .collect(Collectors.toList());

        return BookResponseDTO.SuggestBooksResultDTO.builder()
                .books(bookInfoList)
                .build();
    }

    public static BookResponseDTO.BookInfoDTO toBookInfoDTO(Book book) {
        return BookResponseDTO.BookInfoDTO.builder()
                .id(book.getId())
                .cover(book.getCover())
                .title(book.getTitle())
                .author(book.getAuthor())
                .category(book.getBookCategory().getCategoryName())
                .publisher(book.getPublisher())
                .build();
    }
}
