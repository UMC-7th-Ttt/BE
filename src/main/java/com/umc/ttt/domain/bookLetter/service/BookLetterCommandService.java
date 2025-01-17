package com.umc.ttt.domain.bookLetter.service;

import com.umc.ttt.domain.bookLetter.dto.BookLetterRequestDTO;
import com.umc.ttt.domain.bookLetter.entity.BookLetter;
import org.springframework.data.domain.Page;

public interface BookLetterCommandService {
    BookLetter addBookLetter(BookLetterRequestDTO.CRDto request);
    BookLetter updateBookLetter(Long bookLetterId ,BookLetterRequestDTO.CRDto request);
    void deleteBookLetter(Long bookLetterId);
    Page<BookLetter> getBookLetterPreViewList(Integer page);
    BookLetter getBookLetter(Long bookLetterId);
}
