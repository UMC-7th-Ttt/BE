package com.umc.ttt.domain.bookLetter.bookLetterRepository;

import com.umc.ttt.domain.bookLetter.entity.BookLetter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLetterRepository extends JpaRepository<BookLetter, Long> {
}
