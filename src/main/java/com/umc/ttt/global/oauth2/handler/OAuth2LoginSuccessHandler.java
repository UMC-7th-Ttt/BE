package com.umc.ttt.global.oauth2.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc.ttt.domain.member.entity.enums.Role;
import com.umc.ttt.domain.member.repository.MemberRepository;
import com.umc.ttt.global.apiPayload.ApiResponse;
import com.umc.ttt.global.jwt.service.JwtService;
import com.umc.ttt.global.oauth2.CustomOAuth2User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final MemberRepository memberRepository;

    @Value("${jwt.access.expiration}")
    private String accessTokenExpiration;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login 성공!");
        try {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

            // User의 Role이 GUEST일 경우 처음 요청한 회원이므로 회원가입 페이지로 리다이렉트
            // 위에 말은 틀린것같고 guest면 온보딩으로 넘어가면 됨.
            if(oAuth2User.getRole() == Role.GUEST) {
                loginSuccess(response, oAuth2User); // 로그인에 성공한 경우 access, refresh 토큰 생성

//                User findUser = userRepository.findByEmail(oAuth2User.getEmail())
//                                .orElseThrow(() -> new IllegalArgumentException("이메일에 해당하는 유저가 없습니다."));
//                findUser.authorizeUser();
            } else {
                loginSuccess(response, oAuth2User); // 로그인에 성공한 경우 access, refresh 토큰 생성
            }
        } catch (Exception e) {
            throw e;
        }

    }

    // TODO : 소셜 로그인 시에도 무조건 토큰 생성하지 말고 JWT 인증 필터처럼 RefreshToken 유/무에 따라 다르게 처리해보기
    private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
        String email = oAuth2User.getEmail();
        String accessToken = jwtService.createAccessToken(email);
        String refreshToken = jwtService.createRefreshToken(); // JwtService의 createRefreshToken을 사용하여 RefreshToken 발급

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
        jwtService.updateRefreshToken(email, refreshToken);

        log.info("구글로그인에 성공하였습니다. 이메일 : {}", email);
        log.info("구글로그인에 성공하였습니다. AccessToken : {}", accessToken);
        log.info("발급된 AccessToken 만료 기간 : {}", accessTokenExpiration);

        // 응답 바디에 ApiResponse 추가
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ApiResponse apiResponse = ApiResponse.onSuccess("소셜로그인에 성공했습니다!");

        try {
            ObjectMapper objectMapper = new ObjectMapper(); // JSON 변환을 위한 ObjectMapper 생성
            String jsonResponse = objectMapper.writeValueAsString(apiResponse); // ApiResponse를 JSON으로 변환
            response.getWriter().write(jsonResponse); // 응답 바디에 JSON 작성
        } catch (Exception e) {
            log.error("응답 바디 작성 중 오류 발생", e);
        }
    }
}
