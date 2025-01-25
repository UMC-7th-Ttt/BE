package com.umc.ttt.domain.bookClub.converter;

import com.umc.ttt.domain.book.dto.BookResponseDTO;
import com.umc.ttt.domain.bookClub.dto.BookClubRequestDTO;
import com.umc.ttt.domain.bookClub.dto.BookClubResponseDTO;
import com.umc.ttt.domain.bookClub.entity.BookClub;
import com.umc.ttt.domain.bookLetter.entity.BookLetterBook;
import com.umc.ttt.domain.member.dto.MemberResponseDTO;

import java.util.List;

public class BookClubConverter {
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

    public static BookClubResponseDTO.getBookClubDetailsResultDTO toGetBookClubDetailsResultDTO(
            BookClub bookClub, BookResponseDTO.BookInfoDTO bookInfoDTO, List<MemberResponseDTO.MemberInfoDTO> memberInfoDTOList, int elapsedWeeks, int myCompletionRate, int recommendedCompletionRate) {

        return BookClubResponseDTO.getBookClubDetailsResultDTO.builder()
                .bookClubId(bookClub.getId())
                .elapsedWeeks(elapsedWeeks)
                .myCompletionRate(myCompletionRate)
                .recommendedCompletionRate(recommendedCompletionRate)
                .bookInfo(bookInfoDTO)
                .members(memberInfoDTOList)
                .build();
    }
}
