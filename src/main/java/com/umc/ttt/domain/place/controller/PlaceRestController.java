package com.umc.ttt.domain.place.controller;

import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.member.repository.MemberRepository;
import com.umc.ttt.domain.place.dto.PlaceRequestDTO;
import com.umc.ttt.domain.place.dto.PlaceResponseDTO;
import com.umc.ttt.domain.place.service.PlaceApiService;
import com.umc.ttt.domain.place.service.PlaceCommandService;
import com.umc.ttt.domain.place.service.PlaceQueryService;
import com.umc.ttt.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/places")
public class PlaceRestController {

    private final PlaceApiService placeApiService;
    private final PlaceCommandService placeCommandService;
    private final PlaceQueryService placeQueryService;
    private final MemberRepository memberRepository;

    @PostMapping
    @Operation(summary = "독립서점, 북카페 Open API 데이터 저장", description = "서버 테스트용 api입니다. 연동x")
    public ApiResponse<String> fetchBooks() throws Exception {
        placeApiService.fetchAndSaveOpenApiData();
        return ApiResponse.onSuccess("독립서점, 북카페 Open API 데이터가 저장되었습니다.");
    }

    @PatchMapping("/images")
    @Operation(summary = "장소 이미지 데이터 저장 - Naver API", description = "서버 테스트용 api입니다. 연동x")
    public ApiResponse<String> updateImagesForAllPlaces() {
        placeApiService.updateImagesForAllPlaces();
        return ApiResponse.onSuccess("모든 장소의 이미지가 업데이트되었습니다.");
    }

    @PatchMapping("/{placeId}/curations")
    @Operation(summary = "장소 큐레이션 작성, 수정", description = "관리자만 작성 및 수정 가능합니다. 삭제의 경우 빈 문자열을 전달해주세요.")
    public ApiResponse<PlaceResponseDTO.CurationDTO> updateCuration(@PathVariable(name = "placeId") Long placeId,
                                                                    @Valid @RequestBody PlaceRequestDTO.CurationDTO curationDTO) {
        // TODO: 로그인한 회원 정보로 변경
        Member member = memberRepository.findById(1L).get();
        return ApiResponse.onSuccess(placeCommandService.updateCuration(placeId, curationDTO, member));
    }

    @GetMapping("/{placeId}")
    @Operation(summary = "장소 상세 조회")
    public ApiResponse<PlaceResponseDTO.PlaceDTO> getPlace(@PathVariable(name = "placeId") Long placeId) {
        // TODO: 로그인한 회원 정보로 변경
        Member member = memberRepository.findById(1L).get();
        return ApiResponse.onSuccess(placeQueryService.getPlace(placeId, member));
    }
}
