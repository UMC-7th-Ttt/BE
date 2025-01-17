package com.umc.ttt.domain.bookLetter.Controller;

import com.umc.ttt.domain.bookLetter.Converter.BookLetterConverter;
import com.umc.ttt.domain.bookLetter.dto.BookLetterRequestDTO;
import com.umc.ttt.domain.bookLetter.dto.BookLetterResponseDTO;
import com.umc.ttt.domain.bookLetter.entity.BookLetter;
import com.umc.ttt.domain.bookLetter.service.BookLetterCommandService;
import com.umc.ttt.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book-letters")
public class BookLetterController {
    private final BookLetterCommandService bookLetterCommandService;

    // 북레터 작성
    @PostMapping("/")
    @Operation(summary = "북레터 작성",description = "작성한 북레터를 저장하는 API입니다.")
    public ApiResponse<BookLetterResponseDTO.CRDResultDTO> addBookLetter(@RequestBody @Valid BookLetterRequestDTO.CRDto request) {
        BookLetter bookLetter = bookLetterCommandService.addBookLetter(request);
        return ApiResponse.onSuccess(BookLetterConverter.toCRResultDTO(bookLetter));
    }

    // 븍레터 수정
    @PatchMapping("/{bookLetterId}")
    @Operation(summary = "북레터 수정",description = "북레터를 수정하는 API입니다.")
    public ApiResponse<BookLetterResponseDTO.CRDResultDTO> modifyBookLetter(
            @PathVariable(name = "bookLetterId") Long bookLetterId,
            @RequestBody @Valid BookLetterRequestDTO.CRDto request){
        BookLetter bookLetter = bookLetterCommandService.updateBookLetter(bookLetterId, request);
        return ApiResponse.onSuccess(BookLetterConverter.toCRResultDTO(bookLetter));
    }

    // 북레터 삭제
    @DeleteMapping("/{bookLetterId}")
    @Operation(summary = "북레터 삭제",description = "북레터를 삭제하는 API입니다.")
    public ApiResponse<Void> deleteBookLetter(@PathVariable(name = "bookLetterId")Long bookLetterId){
        bookLetterCommandService.deleteBookLetter(bookLetterId);
        return ApiResponse.onSuccess(null);
    }
}
