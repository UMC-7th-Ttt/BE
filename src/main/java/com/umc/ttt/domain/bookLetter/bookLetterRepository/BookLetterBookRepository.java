package com.umc.ttt.domain.bookLetter.bookLetterRepository;

import com.umc.ttt.domain.bookLetter.entity.BookLetter;
import com.umc.ttt.domain.bookLetter.entity.BookLetterBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookLetterBookRepository extends JpaRepository<BookLetterBook,Long> {
}
