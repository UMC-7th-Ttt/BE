package com.umc.ttt.domain.scrap.controller;

import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.member.repository.MemberRepository;
import com.umc.ttt.domain.scrap.dto.ScrapResponseDTO;
import com.umc.ttt.domain.scrap.service.ScrapCommandService;
import com.umc.ttt.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScrapRestController {

    private final MemberRepository memberRepository;
    private final ScrapCommandService scrapCommandService;

    @GetMapping("places/{placeId}/scraps")
    @Operation(summary = "공간 스크랩", description = "folder에 폴더 이름을 전달해주세요. (ex. 공간)")
    public ApiResponse<ScrapResponseDTO.PlaceScrapDTO> addScrap(@PathVariable(name = "placeId") Long placeId,
                                                                @RequestParam(name = "folder") String folder) {
        // TODO: 로그인한 회원 정보로 변경
        Member member = memberRepository.findById(1L).get();
        return ApiResponse.onSuccess(scrapCommandService.addPlaceScrap(placeId, folder, member));
    }

    @DeleteMapping("/places/{placeId}/scraps")
    @Operation(summary = "공간 스크랩 취소")
    public ApiResponse<ScrapResponseDTO.PlaceScrapDTO> removeScrap(@PathVariable(name = "placeId") Long placeId) {
        // TODO: 로그인한 회원 정보로 변경
        Member member = memberRepository.findById(1L).get();
        return ApiResponse.onSuccess(scrapCommandService.removePlaceScrap(placeId, member));
    }

}
