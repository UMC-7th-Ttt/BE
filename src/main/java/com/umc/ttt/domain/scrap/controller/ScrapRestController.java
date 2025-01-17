package com.umc.ttt.domain.scrap.controller;

import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.member.repository.MemberRepository;
import com.umc.ttt.domain.scrap.dto.ScrapResponseDTO;
import com.umc.ttt.domain.scrap.service.ScrapCommandService;
import com.umc.ttt.domain.scrap.service.ScrapQueryService;
import com.umc.ttt.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScrapRestController {

    private final MemberRepository memberRepository;
    private final ScrapCommandService scrapCommandService;
    private final ScrapQueryService scrapQueryService;

    @GetMapping("/scraps/folders")
    @Operation(summary = "스크랩 폴더 목록 조회", description = "도서, 공간은 기본 폴더입니다.")
    public ApiResponse<List<ScrapResponseDTO.ScrapFolderDTO>> getScrapFolders() {
        // TODO: 로그인한 회원 정보로 변경
        Member member = memberRepository.findById(1L).get();
        return ApiResponse.onSuccess(scrapQueryService.getScrapFolders(member));
    }

    @GetMapping("/scraps/folders/{folderId}")
    @Operation(summary = "특정 폴더의 스크랩 내역 조회", description = "무한 스크롤 방식으로 스크랩 내역을 조회합니다.\n\n" +
            "첫 페이지 조회 시 각 cursor 값으로 0을 전달해주세요.\n\n" +
            "첫 페이지가 아닌 경우 이전 응답의 hasNext가 true일 때, nextBookCursor, nextPlaceCursor 값을 각 cursor로 전달해주세요.")
    public ApiResponse<ScrapResponseDTO.ScrapListDTO> getScrapList(@PathVariable(name = "folderId") Long folderId,
                                                                           @RequestParam(name = "bookCursor", required = false) Long bookCursor,
                                                                           @RequestParam(name = "placeCursor", required = false) Long placeCursor,
                                                                           @RequestParam(name = "limit", defaultValue = "10") Integer limit) {
        // TODO: 로그인한 회원 정보로 변경
        Member member = memberRepository.findById(1L).get();
        return ApiResponse.onSuccess(scrapQueryService.getScrapList(folderId, bookCursor, placeCursor, limit, member));
    }

    @PostMapping("/scraps/folders")
    @Operation(summary = "스크랩 폴더 생성", description = "마이페이지에서 스크랩 폴더 생성 시 사용되는 api입니다.")
    public ApiResponse<ScrapResponseDTO.ScrapFolderDTO> createScrapFolder(@RequestParam(name = "folder") String folder) {
        // TODO: 로그인한 회원 정보로 변경
        Member member = memberRepository.findById(1L).get();
        return ApiResponse.onSuccess(scrapCommandService.createScrapFolder(folder, member));
    }

    @DeleteMapping("/scraps/folders/{folderId}")
    @Operation(summary = "스크랩 폴더 삭제", description = "폴더의 스크랩 내역까지 모두 삭제됩니다. 기본 폴더(도서, 공간)는 삭제할 수 없습니다.")
    public ApiResponse<Long> deleteScrapFolder(@PathVariable(name = "folderId") Long folderId) {
        // TODO: 로그인한 회원 정보로 변경
        Member member = memberRepository.findById(1L).get();
        return ApiResponse.onSuccess(scrapCommandService.deleteScrapFolder(folderId, member));
    }

    @PostMapping("places/{placeId}/scraps")
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
