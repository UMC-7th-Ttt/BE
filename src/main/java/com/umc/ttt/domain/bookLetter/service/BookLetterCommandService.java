package com.umc.ttt.domain.bookLetter.service;

import com.umc.ttt.domain.bookLetter.dto.BookLetterRequestDTO;
import com.umc.ttt.domain.bookLetter.entity.BookLetter;

public interface BookLetterCommandService {
    public BookLetter addBookLetter(BookLetterRequestDTO.CRDto request);
    public BookLetter updateBookLetter(Long bookLetterId ,BookLetterRequestDTO.CRDto request);
    public void deleteBookLetter(Long bookLetterId);
}
