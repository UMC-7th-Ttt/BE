package com.umc.ttt.domain.bookClub.converter;

import com.umc.ttt.domain.bookClub.dto.BookClubRequestDTO;
import com.umc.ttt.domain.bookClub.dto.BookClubResponseDTO;
import com.umc.ttt.domain.bookClub.entity.BookClub;
import com.umc.ttt.domain.bookLetter.entity.BookLetterBook;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class BookClubConvert {
    public static BookClubResponseDTO.AddUpdateResultDTO addUpdateResultDTO(BookClub bookClub) {
        return BookClubResponseDTO.AddUpdateResultDTO.builder()
                .bookClubId(bookClub.getId())
                .build();
    }

    public static BookClub toBookClub(BookClubRequestDTO.AddUpdateDTO request, BookLetterBook bookLetterBook) {
        return BookClub.builder()
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .comment(request.getComment())
                .bookLetterBook(bookLetterBook)
                .recruitNumber(request.getRecruitNumber())
                .build();
    }

    // 북클럽 리스트 조회
    public static BookClubResponseDTO.BookClubPreViewDTO bookClubPreViewDTO(BookClub bookClub) {
        return BookClubResponseDTO.BookClubPreViewDTO.builder()
                .bookClubId(bookClub.getId())
                .title(bookClub.getBookLetterBook().getBook().getTitle())
                .build();
    }

    public static BookClubResponseDTO.BookClubListDTO bookClubListDTO(Page<BookClub> bookClubList) {
        List<BookClubResponseDTO.BookClubPreViewDTO> bookClubPreViewDTOList = bookClubList.stream()
                .map(BookClubConvert::bookClubPreViewDTO).collect(Collectors.toList());

        return BookClubResponseDTO.BookClubListDTO.builder()
                .isLastPage(bookClubList.isLast())
                .isFirstPage(bookClubList.isFirst())
                .totalPage(bookClubList.getTotalPages())
                .totalElements(bookClubList.getTotalElements())
                .listSize(bookClubPreViewDTOList.size())
                .bookClubPreViewList(bookClubPreViewDTOList)
                .build();
    }

    // 북레터 상세 보기(관리자)
    public static BookClubResponseDTO.BookClubDTO toBookClubDTO(BookClub bookClub) {
        return BookClubResponseDTO.BookClubDTO.builder()
                .bookLetterId(bookClub.getBookLetterBook().getBookLetter().getId())
                .bookId(bookClub.getBookLetterBook().getBook().getId())
                .title(bookClub.getBookLetterBook().getBook().getTitle())
                .isWriter(true) // 추후 writer인지 확인
                .startDate(bookClub.getStartDate())
                .endDate(bookClub.getEndDate())
                .comment(bookClub.getComment())
                .recuitNumber(bookClub.getRecruitNumber())
                .build();
    }
}
