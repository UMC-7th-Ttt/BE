package com.umc.ttt.domain.bookLetter.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

public class BookLetterRequestDTO {
    @Getter
    public static class CRDto {
        @Size(min = 5, max = 5, message = "한 북레터 당 5권을 제공 필수입니다.")
        List<Long> booksId;

        @NotNull(message = "북레터 제목은 필수입니다.")
        String title;

        @NotNull(message = "북레터 부제목은 필수입니다.")
        String subtitle;

        @NotNull(message = "북레터 에디터는 필수입니다.")
        String editor;

        @NotNull(message = "북레터 콘텐츠 인사말은 필수입니다.")
        String content;

        @NotNull(message = "북레터 표지는 필수입니다.")
        String coverImg;
    }
}
