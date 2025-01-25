package com.umc.ttt.domain.bookClub.dto;

import com.umc.ttt.domain.book.dto.BookResponseDTO;
import com.umc.ttt.domain.member.dto.MemberResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class BookClubResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddUpdateResultDTO {
        Long bookClubId;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getBookClubDetailsResultDTO {
        Long bookClubId;
        Integer elapsedWeeks;
        Integer myCompletionRate;
        Integer recommendedCompletionRate;
        BookResponseDTO.BookInfoDTO bookInfo;
        List<MemberResponseDTO.MemberInfoDTO> members;
    }

}
