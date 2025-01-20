package com.umc.ttt.domain.member.controller;

import com.umc.ttt.domain.member.dto.MemberSignUpDTO;
import com.umc.ttt.domain.member.service.MemberCommandService;
import com.umc.ttt.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberCommandService memberCommandService;

    @PostMapping("/api/sign-up")
    @Operation(summary = "회원가입", description = "서버 테스트용 api입니다. 연동x")
    public ApiResponse<String> signUp(@RequestBody MemberSignUpDTO memberSignUpDTO) throws Exception {
        memberCommandService.signUp(memberSignUpDTO);
        return ApiResponse.onSuccess("회원가입에 성공했습니다!");

    }

    /*요청 URI("/jwt-test")는 SecurityConfig 인증 URI 설정부분에서 설정한
인증 없이 접근 가능한 URI가 아니기 때문에
AccessToken을 헤더에 담아 보내서 인증을 통과해야만 접근이 가능합니다.*/
    //JWT 서비스 테스트를 위한 API
    @GetMapping("/jwt-test")
    @Operation(summary = "jwtTest 요청", description = "서버 테스트용 api입니다. 연동x")
    public ApiResponse<String> jwtTest() {
        return ApiResponse.onSuccess("jwtTest 요청 성공");
    }
}
