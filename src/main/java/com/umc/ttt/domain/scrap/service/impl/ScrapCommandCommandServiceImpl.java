package com.umc.ttt.domain.scrap.service.impl;

import com.umc.ttt.domain.book.entity.Book;
import com.umc.ttt.domain.book.repository.BookRepository;
import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.place.entity.Place;
import com.umc.ttt.domain.place.repository.PlaceRepository;
import com.umc.ttt.domain.scrap.dto.ScrapResponseDTO;
import com.umc.ttt.domain.scrap.entity.BookScrap;
import com.umc.ttt.domain.scrap.entity.ScrapFolder;
import com.umc.ttt.domain.scrap.repository.BookScrapRepository;
import com.umc.ttt.domain.scrap.repository.PlaceScrapRepository;
import com.umc.ttt.domain.scrap.converter.ScrapConverter;
import com.umc.ttt.domain.scrap.entity.PlaceScrap;
import com.umc.ttt.domain.scrap.repository.ScrapFolderRepository;
import com.umc.ttt.domain.scrap.service.ScrapCommandService;
import com.umc.ttt.global.apiPayload.code.status.ErrorStatus;
import com.umc.ttt.global.apiPayload.exception.handler.BookHandler;
import com.umc.ttt.global.apiPayload.exception.handler.PlaceHandler;
import com.umc.ttt.global.apiPayload.exception.handler.ScrapHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScrapCommandCommandServiceImpl implements ScrapCommandService {

    private final PlaceRepository placeRepository;
    private final PlaceScrapRepository placeScrapRepository;
    private final BookRepository bookRepository;
    private final BookScrapRepository bookScrapRepository;
    private final ScrapFolderRepository scrapFolderRepository;

    // TODO: 회원가입 시 기본 폴더(도서, 공간) 생성하는 로직 추가 필요

    @Override
    public ScrapResponseDTO.ScrapFolderDTO createScrapFolder(String folder, Member member) {
        // 폴더가 이미 존재하는지 확인
        if (scrapFolderRepository.existsByMemberAndName(member, folder)) {
            throw new ScrapHandler(ErrorStatus.FOLDER_ALREADY_EXISTS);
        }

        // 폴더 생성
        ScrapFolder scrapFolder = ScrapFolder.builder()
                .name(folder)
                .member(member)
                .build();

        scrapFolderRepository.save(scrapFolder);

        return ScrapConverter.toScrapFolderDTO(scrapFolder);
    }

    @Override
    public Long deleteScrapFolder(Long folderId, Member member) {
        // 폴더가 존재하는지 확인
        ScrapFolder scrapFolder = scrapFolderRepository.findByIdAndMember(folderId, member)
                .orElseThrow(() -> new ScrapHandler(ErrorStatus.FOLDER_NOT_FOUND));

        // 기본 폴더(도서, 공간)는 삭제할 수 없음
        if (scrapFolder.isDefaultFolder()) {
            throw new ScrapHandler(ErrorStatus.CANNOT_DELETE_DEFAULT_FOLDER);
        }

        // 폴더에 포함된 스크랩 내역 삭제(도서, 공간)
        bookScrapRepository.deleteAllByScrapFolder(scrapFolder);
        placeScrapRepository.deleteAllByScrapFolder(scrapFolder);

        // 폴더 삭제
        scrapFolderRepository.delete(scrapFolder);

        return folderId;
    }

    @Override
    public ScrapResponseDTO.PlaceScrapDTO addPlaceScrap(Long placeId, String folder, Member member) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceHandler(ErrorStatus.PLACE_NOT_FOUND));

        if (folder.equals("도서")) {
            throw new ScrapHandler(ErrorStatus.INVALID_FOLDER);
        }

        // 폴더가 이미 존재하는지 확인
        ScrapFolder scrapFolder = scrapFolderRepository.findByMemberAndName(member, folder)
                .orElseGet(() -> {
                    // 폴더가 없으면 새로 생성
                    ScrapFolder newFolder = ScrapFolder.builder()
                            .name(folder)
                            .member(member)
                            .build();
                    return scrapFolderRepository.save(newFolder);
                });

        // 스크랩 내역이 존재하는지 확인(모든 폴더 내에서)
        if (placeScrapRepository.existsByPlace(place)) {
            throw new PlaceHandler(ErrorStatus.SCRAP_ALREADY_EXIST);
        }

        // 스크랩 저장
        PlaceScrap placeScrap = PlaceScrap.builder()
                .place(place)
                .scrapFolder(scrapFolder)
                .build();
        placeScrapRepository.save(placeScrap);

        return ScrapConverter.toPlaceScrapDTO(place, member, true);
    }

    @Override
    public ScrapResponseDTO.BookScrapDTO addBookScrap(Long bookId, String folder, Member member) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookHandler(ErrorStatus.BOOK_NOT_FOUND));

        if (folder.equals("공간")) {
            throw new ScrapHandler(ErrorStatus.INVALID_FOLDER);
        }

        // 폴더가 이미 존재하는지 확인
        ScrapFolder scrapFolder = scrapFolderRepository.findByMemberAndName(member, folder)
                .orElseGet(() -> {
                    // 폴더가 없으면 새로 생성
                    ScrapFolder newFolder = ScrapFolder.builder()
                            .name(folder)
                            .member(member)
                            .build();
                    return scrapFolderRepository.save(newFolder);
                });

        // 스크랩 내역이 존재하는지 확인(모든 폴더 내에서)
        if (bookScrapRepository.existsByBook(book)) {
            throw new BookHandler(ErrorStatus.SCRAP_ALREADY_EXIST);
        }

        // 스크랩 저장
        BookScrap bookScrap = BookScrap.builder()
                .book(book)
                .scrapFolder(scrapFolder)
                .build();
        bookScrapRepository.save(bookScrap);

        return ScrapConverter.toBookScrapDTO(book, member, true);
    }

    @Override
    public ScrapResponseDTO.PlaceScrapDTO removePlaceScrap(Long placeId, Member member) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceHandler(ErrorStatus.PLACE_NOT_FOUND));

        PlaceScrap placeScrap = placeScrapRepository.findByPlace(place)
                .orElseThrow(() -> new PlaceHandler(ErrorStatus.SCRAP_NOT_FOUND));

        placeScrapRepository.delete(placeScrap);

        return ScrapConverter.toPlaceScrapDTO(place, member, false);
    }

    @Override
    public ScrapResponseDTO.BookScrapDTO removeBookScrap(Long bookId, Member member) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookHandler(ErrorStatus.BOOK_NOT_FOUND));

        BookScrap bookScrap = bookScrapRepository.findByBook(book)
                .orElseThrow(() -> new BookHandler(ErrorStatus.BOOK_NOT_FOUND));

        bookScrapRepository.delete(bookScrap);

        return ScrapConverter.toBookScrapDTO(book, member, false);
    }

}
