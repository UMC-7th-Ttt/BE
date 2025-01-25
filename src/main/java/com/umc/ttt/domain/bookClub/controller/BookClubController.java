package com.umc.ttt.domain.bookClub.controller;

import com.umc.ttt.domain.bookClub.converter.BookClubConvert;
import com.umc.ttt.domain.bookClub.dto.BookClubRequestDTO;
import com.umc.ttt.domain.bookClub.dto.BookClubResponseDTO;
import com.umc.ttt.domain.bookClub.entity.BookClub;
import com.umc.ttt.domain.bookClub.service.BookClubService;
import com.umc.ttt.domain.bookLetter.validation.annotataion.CheckPage;
import com.umc.ttt.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book-clubs")
public class BookClubController {
    private final BookClubService bookClubService;

    @PostMapping("/")
    @Operation(summary = "북클럽 작성",description = "북클럽을 저장하는 API입니다.")
    public ApiResponse<BookClubResponseDTO.AddUpdateResultDTO> add(@RequestBody @Valid BookClubRequestDTO.AddUpdateDTO request) {
        BookClub bookClub = bookClubService.addBookClub(request);
        return ApiResponse.onSuccess(BookClubConvert.addUpdateResultDTO(bookClub));
    }

    @PatchMapping("/{bookClubId}")
    @Operation(summary = "북클럽 수정",description = "북클럽을 수정하는 API입니다.")
    public ApiResponse<BookClubResponseDTO.AddUpdateResultDTO> update(@PathVariable(name="bookClubId") Long bookClubId, @RequestBody @Valid BookClubRequestDTO.AddUpdateDTO request) {
        BookClub bookClub = bookClubService.updateBookClub(bookClubId,request);
        return ApiResponse.onSuccess(BookClubConvert.addUpdateResultDTO(bookClub));
    }

    @DeleteMapping("/{bookClubId}")
    @Operation(summary = "북클럽 삭제",description = "북클럽을 삭제하는 API입니다.")
    public ApiResponse<Void> deleteBookClub(@PathVariable(name="bookClubId") Long bookClubId){
        bookClubService.deleteBookClub(bookClubId);
        return ApiResponse.onSuccess(null);
    }

    @GetMapping("/")
    @Operation(summary = "북클럽 리스트 조회",description = "북레터 리스트를 조회하는 API입니다.")
    public ApiResponse<BookClubResponseDTO.BookClubListDTO> getBookClubPreviewList(@CheckPage @RequestParam(name="page",defaultValue = "1")Integer page){
        Page<BookClub> bookClubList = bookClubService.getBookClubPreViewList(page-1);
        return ApiResponse.onSuccess(BookClubConvert.bookClubListDTO(bookClubList));
    }

    @GetMapping("/{bookClubId}")
    @Operation(summary = "북클럽 상세 조회",description = "특정 북클럽 리스트를 조회하는 API입니다.")
    public ApiResponse<BookClubResponseDTO.BookClubDTO> getBookClub(@PathVariable(name="bookClubId") Long bookClubId){
        BookClub bookClub = bookClubService.getBookClub(bookClubId);
        return ApiResponse.onSuccess(BookClubConvert.toBookClubDTO(bookClub));
    }
}
