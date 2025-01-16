package com.umc.ttt.domain.book.converter;

import com.umc.ttt.domain.book.dto.BookResponseDTO;
import com.umc.ttt.domain.book.entity.Book;
import com.umc.ttt.domain.book.entity.BookCategory;

public class BookConverter {

    public static Book toEntity(BookResponseDTO.Item item, BookCategory bookCategory) {
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
}
