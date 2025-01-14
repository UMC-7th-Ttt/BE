package com.umc.ttt.domain.book.converter;

import com.umc.ttt.domain.book.dto.BookResponseDTO;
import com.umc.ttt.domain.book.entity.Book;

public class BookConverter {

    public static Book toEntity(BookResponseDTO.Item item) {
        return Book.builder()
                .title(item.getTitle())
                .author(item.getAuthor())
                .isbn(item.getIsbn())
                .cover(item.getCover())
                .description(item.getDescription())
                .publisher(item.getPublisher())
                .categoryName(item.getCategoryName())
                .bestRank(item.getBestRank())
                .link(item.getLink())
                .build();
    }
}
