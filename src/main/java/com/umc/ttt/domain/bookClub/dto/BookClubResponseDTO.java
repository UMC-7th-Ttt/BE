package com.umc.ttt.domain.bookClub.dto;

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

    // 북클럽 리스트(관리자)
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookClubListDTO{
        List<BookClubPreViewDTO> bookClubPreViewList;
        Integer totalPage;
        Integer listSize;
        Long totalElements;
        Boolean isFirstPage;
        Boolean isLastPage;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookClubPreViewDTO{
        Long bookClubId;
        String title;
    }

    // 북클럽 상세 페이지(관리자)



}
