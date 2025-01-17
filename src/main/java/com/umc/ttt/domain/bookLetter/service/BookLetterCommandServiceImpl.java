package com.umc.ttt.domain.bookLetter.service;

import com.umc.ttt.domain.book.entity.Book;
import com.umc.ttt.domain.book.repository.BookRepository;
import com.umc.ttt.domain.bookLetter.Converter.BookLetterConverter;
import com.umc.ttt.domain.bookLetter.bookLetterRepository.BookLetterRepository;
import com.umc.ttt.domain.bookLetter.dto.BookLetterRequestDTO;
import com.umc.ttt.domain.bookLetter.entity.BookLetter;
import com.umc.ttt.global.apiPayload.code.status.ErrorStatus;
import com.umc.ttt.global.apiPayload.exception.handler.BookHandler;
import com.umc.ttt.global.apiPayload.exception.handler.BookLetterHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookLetterCommandServiceImpl implements BookLetterCommandService {
    private final BookLetterRepository bookLetterRepository;
    private final BookRepository bookRepository;

    // 북레터 추가
    @Override
    @Transactional
    public BookLetter addBookLetter(BookLetterRequestDTO.CRDto request){
        List<Book> books = request.getBooksId().stream()
                .map(bookId -> {
                    return bookRepository.findById(bookId).orElseThrow(()-> new BookHandler(ErrorStatus.BOOK_NOT_FOUND));
                }).collect(Collectors.toList());

        if(books.size() > 5){
            throw new BookLetterHandler(ErrorStatus.BOOKLETTER_BOOKLIST_LIMIT_EXCEEDED);
        }

        BookLetter bookLetter = BookLetterConverter.toBookLetter(request,books);
        return bookLetterRepository.save(bookLetter);
    }

    // 북레터 수정
    @Override
    @Transactional
    public BookLetter updateBookLetter(Long bookLetterId ,BookLetterRequestDTO.CRDto request){
        BookLetter bookLetter = bookLetterRepository.findById(bookLetterId)
                .orElseThrow(()->new BookLetterHandler(ErrorStatus.BOOKLETTER_NOT_FOUND));

        List<Book> books = request.getBooksId().stream()
                .map(bookId -> {
                    return bookRepository.findById(bookId).orElseThrow(()-> new BookHandler(ErrorStatus.BOOK_NOT_FOUND));
                }).collect(Collectors.toList());

        if(books.size() > 5){
            throw new BookLetterHandler(ErrorStatus.BOOKLETTER_BOOKLIST_LIMIT_EXCEEDED);
        }

        bookLetter.setBooks(books);
        bookLetter.setTitle(request.getTitle());
        bookLetter.setSubtitle(request.getSubtitle());
        bookLetter.setEditor(request.getEditor());
        bookLetter.setContent(request.getContent());
        bookLetter.setCoverImg(request.getCoverImg());

        return bookLetterRepository.save(bookLetter);
    }

    // 북레터 삭제
    @Override
    public void deleteBookLetter(Long bookLetterId) {
        if(!bookLetterRepository.existsById(bookLetterId)){
            throw new BookLetterHandler(ErrorStatus.BOOKLETTER_NOT_FOUND);
        }
        bookLetterRepository.deleteById(bookLetterId);
    }

    // 북레터 리스트
    @Override
    @Transactional
    public Page<BookLetter> getBookLetterPreViewList(Integer page){
        Page<BookLetter> bookLetterPreviewPage = bookLetterRepository.findAll(PageRequest.of(page,10));
        return bookLetterPreviewPage;
    }
}
