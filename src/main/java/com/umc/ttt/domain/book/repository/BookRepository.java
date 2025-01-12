package com.umc.ttt.domain.book.repository;

import com.umc.ttt.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
