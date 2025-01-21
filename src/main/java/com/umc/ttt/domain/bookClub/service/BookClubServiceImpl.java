package com.umc.ttt.domain.bookClub.service;

import com.umc.ttt.domain.bookClub.converter.BookClubConvert;
import com.umc.ttt.domain.bookClub.dto.BookClubRequestDTO;
import com.umc.ttt.domain.bookClub.entity.BookClub;
import com.umc.ttt.domain.bookClub.handler.BookClubHandler;
import com.umc.ttt.domain.bookClub.repository.BookClubRepository;
import com.umc.ttt.domain.bookLetter.bookLetterRepository.BookLetterBookRepository;
import com.umc.ttt.domain.bookLetter.entity.BookLetterBook;
import com.umc.ttt.domain.bookLetter.handler.BookLetterBookHandler;
import com.umc.ttt.global.apiPayload.code.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookClubServiceImpl implements BookClubService{
    private final BookClubRepository bookClubRepository;
    private final BookLetterBookRepository bookLetterBookRepository;

    @Override
    @Transactional
    public BookClub addBookClub(BookClubRequestDTO.AddUpdateDTO request) {
        BookLetterBook bookLetterBook = bookLetterBookRepository.findById(request.getBookLetterBookId()).orElseThrow(() -> new BookLetterBookHandler(ErrorStatus.BOOK_LETTER_BOOK_NOT_FOUND));

        boolean existBookClub = bookClubRepository.existsByBookLetterBook(bookLetterBook);
        if(existBookClub) {
            throw new BookLetterBookHandler(ErrorStatus.BOOK_LETTER_BOOK_ALREADY_EXIST);
        }
        BookClub bookClub = BookClubConvert.toBookClub(request, bookLetterBook);

        return bookClubRepository.save(bookClub);
    }

    @Override
    @Transactional
    public BookClub updateBookClub(Long bookClubId, BookClubRequestDTO.AddUpdateDTO request) {
        BookClub bookClub = bookClubRepository.findById(bookClubId).orElseThrow(()->new BookClubHandler(ErrorStatus.BOOK_CLUB_NOT_FOUND));
        BookLetterBook bookLetterBook = bookLetterBookRepository.findById(request.getBookLetterBookId()).orElseThrow(() -> new BookLetterBookHandler(ErrorStatus.BOOK_LETTER_BOOK_NOT_FOUND));

        boolean existBookClub = bookClubRepository.existsByBookLetterBook(bookLetterBook);
        if(existBookClub) {
            throw new BookLetterBookHandler(ErrorStatus.BOOK_LETTER_BOOK_ALREADY_EXIST);
        }

        bookClub.setBookLetterBook(bookLetterBook);
        bookClub.setStartDate(request.getStartDate());
        bookClub.setEndDate(request.getEndDate());
        bookClub.setComment(request.getComment());

        return bookClubRepository.save(bookClub);
    }

    @Override
    public void deleteBookClub(Long bookClubId) {
        if(!bookClubRepository.existsById(bookClubId)) {
            throw new BookClubHandler(ErrorStatus.BOOK_CLUB_NOT_FOUND);
        }
        bookClubRepository.deleteById(bookClubId);
    }
}
