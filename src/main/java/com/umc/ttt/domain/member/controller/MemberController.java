package com.umc.ttt.domain.member.controller;

import com.umc.ttt.domain.member.converter.MemberConverter;
import com.umc.ttt.domain.member.dto.MemberSignUpDTO;
import com.umc.ttt.domain.member.dto.TokenResponseDTO;
import com.umc.ttt.domain.member.service.MemberCommandService;
import com.umc.ttt.global.apiPayload.ApiResponse;
import com.umc.ttt.global.jwt.repository.RefreshTokenRepository;
import com.umc.ttt.global.jwt.service.JwtService;
import com.umc.ttt.global.jwt.service.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberCommandService memberCommandService;
    private final JwtService jwtService;
    private final RefreshTokenService tokenService;
    private final RefreshTokenRepository tokenRepository;

    @PostMapping("/api/sign-up")
    @Operation(summary = "회원가입", description = "회원가입 API입니다. ")
    public ApiResponse<String> signUp(@RequestBody @Valid MemberSignUpDTO memberSignUpDTO) throws Exception {
        memberCommandService.signUp(memberSignUpDTO);
        return ApiResponse.onSuccess("회원가입에 성공했습니다!");
    }


    @DeleteMapping("/api/sign-out")
    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴 시 사용하는 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER401", description = "사용자가 없습니다", content = @Content(mediaType = "application/json")),
    })
    public ApiResponse<String> signOut(@RequestHeader("Authorization") String token) throws Exception {
        String jwtToken = token.substring(7);
        var userEmail = jwtService.extractEmail(jwtToken);
        memberCommandService.signOut(userEmail);
        return ApiResponse.onSuccess("회원 탈퇴에 성공했습니다!");
    }


    //JWT 서비스 테스트를 위한 API
    @GetMapping("/jwt-test")
    @Operation(summary = "jwtTest 요청", description = "서버 테스트용 api입니다. 연동x")
    public ApiResponse<String> jwtTest() {
        return ApiResponse.onSuccess("jwtTest 요청 성공");
    }

    @PostMapping("/token/logout")
    @Operation(summary = "로그아웃",description = "로그아웃 하는 API입니다.accessToken 필요")
    public ApiResponse<String> logout(@RequestHeader("Authorization") String accessToken) {
        // accessToken으로 현재 Redis 정보 삭제
        String jwtToken = accessToken.substring(7);
        tokenService.removeRefreshToken(jwtToken);
        return ApiResponse.onSuccess("로그아웃에 성공하였습니다.");
    }

    @PostMapping("/token/refresh")
    @Operation(summary = "액세스 토큰 재발급", description = "accessToken 재발급하는 API입니다.accessToken 필요")
    public ApiResponse<TokenResponseDTO.UpdateResultDTO> refresh(@RequestHeader("Authorization") String accessToken) throws Exception {
        String jwtToken = accessToken.substring(7); // "Bearer " 제거
        log.info("AccessToken: {}", jwtToken);

        String newAccessToken = memberCommandService.refreshAccessToken(jwtToken);
        return ApiResponse.onSuccess(MemberConverter.updateResultDTO(newAccessToken));
    }

}
