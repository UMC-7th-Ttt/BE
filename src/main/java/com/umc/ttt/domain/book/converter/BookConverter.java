package com.umc.ttt.domain.book.converter;

import com.umc.ttt.domain.book.dto.BookFetchDTO;
import com.umc.ttt.domain.book.dto.BookResponseDTO;
import com.umc.ttt.domain.book.entity.Book;
import com.umc.ttt.domain.book.entity.BookCategory;
import com.umc.ttt.domain.member.entity.Member;

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
                .hasEbook(item.isHasEbook())
                .bookCategory(bookCategory)
                .build();
    }

    public static BookResponseDTO.SearchBookResultDTO toSearchBooksResultDTO(List<Book> books, long nextCursor, int limit, boolean hasNext, List<Long> scrapedBookIds) {
        List<BookResponseDTO.BookInfoDTO> bookInfoList = books.stream()
                .map(book -> toBookInfoDTO(book, scrapedBookIds.contains(book.getId())))
                .collect(Collectors.toList());

        return BookResponseDTO.SearchBookResultDTO.builder()
                .books(bookInfoList)
                .nextCursor(nextCursor)
                .limit(limit)
                .hasNext(hasNext)
                .build();
    }

    public static BookResponseDTO.SuggestBooksResultDTO toSuggestBooksResultDTO(List<Book> books, List<Long> scrapedBookIds) {
        List<BookResponseDTO.BookInfoDTO> bookInfoList = books.stream()
                .map(book -> toBookInfoDTO(book, scrapedBookIds.contains(book.getId())))
                .collect(Collectors.toList());

        return BookResponseDTO.SuggestBooksResultDTO.builder()
                .books(bookInfoList)
                .build();
    }

    private static BookResponseDTO.BookInfoDTO toBookInfoDTO(Book book, boolean isScraped) {
        return BookResponseDTO.BookInfoDTO.builder()
                .id(book.getId())
                .cover(book.getCover())
                .title(book.getTitle())
                .author(book.getAuthor())
                .category(book.getBookCategory().getCategoryName())
                .publisher(book.getPublisher())
                .isScraped(isScraped)
                .build();
    }

    public static BookResponseDTO.GetBookDetailResultDTO toGetBookDetailResultDTO(Book book, Member member, boolean isScraped) {
        return BookResponseDTO.GetBookDetailResultDTO.builder()
                .id(book.getId())
                .cover(book.getCover())
                .title(book.getTitle())
                .author(book.getAuthor())
                .category(book.getBookCategory().getCategoryName())
                .publisher(book.getPublisher())
                .itemPage(book.getItemPage())
                .description(book.getDescription())
                .hasEbook(book.isHasEbook())
                .isScraped(isScraped)
                .build();
    }
}
