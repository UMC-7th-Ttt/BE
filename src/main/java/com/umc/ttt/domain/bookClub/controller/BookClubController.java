package com.umc.ttt.domain.bookClub.controller;

import com.umc.ttt.domain.bookClub.converter.BookClubConverter;
import com.umc.ttt.domain.bookClub.dto.BookClubRequestDTO;
import com.umc.ttt.domain.bookClub.dto.BookClubResponseDTO;
import com.umc.ttt.domain.bookClub.entity.BookClub;
import com.umc.ttt.domain.bookClub.service.BookClubQueryService;
import com.umc.ttt.domain.bookClub.service.BookClubService;
import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.member.repository.MemberRepository;
import com.umc.ttt.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book-clubs")
public class BookClubController {
    private final BookClubService bookClubService;
    private final BookClubQueryService bookClubQueryService;
    private final MemberRepository memberRepository;

    @PostMapping("/")
    @Operation(summary = "북클럽 작성",description = "북클럽을 저장하는 API입니다.")
    public ApiResponse<BookClubResponseDTO.AddUpdateResultDTO> add(@RequestBody @Valid BookClubRequestDTO.AddUpdateDTO request) {
        BookClub bookClub = bookClubService.addBookClub(request);
        return ApiResponse.onSuccess(BookClubConverter.addUpdateResultDTO(bookClub));
    }

    @PatchMapping("/{bookClubId}")
    @Operation(summary = "북클럽 수정",description = "북클럽을 수정하는 API입니다.")
    public ApiResponse<BookClubResponseDTO.AddUpdateResultDTO> update(@PathVariable(name="bookClubId") Long bookClubId, @RequestBody @Valid BookClubRequestDTO.AddUpdateDTO request) {
        BookClub bookClub = bookClubService.updateBookClub(bookClubId,request);
        return ApiResponse.onSuccess(BookClubConverter.addUpdateResultDTO(bookClub));
    }

    @DeleteMapping("/{bookClubId}")
    @Operation(summary = "북클럽 삭제",description = "북클럽을 삭제하는 API입니다.")
    public ApiResponse<Void> deleteBookClub(@PathVariable(name="bookClubId") Long bookClubId){
        bookClubService.deleteBookClub(bookClubId);
        return ApiResponse.onSuccess(null);
    }

    @GetMapping("/{bookClubId}/details")
    @Operation(summary = "책마다 북클럽 상세 페이지 조회",description = "책마다 북클럽 상세 페이지를 조회하는 API입니다. 책 정보, 사용자 및 권장 완독률, 멤버 리스트를 제공합니다.")
    public ApiResponse<BookClubResponseDTO.getBookClubDetailsResultDTO> getBookClubDetails(@PathVariable(name="bookClubId") Long bookClubId) {
        // TODO: 로그인한 회원 정보로 변경
        Member member = memberRepository.findById(1L).get();
        BookClubResponseDTO.getBookClubDetailsResultDTO bookClub = bookClubQueryService.getBookClubDetails(bookClubId, member);
        return ApiResponse.onSuccess(bookClub);
    }
}
