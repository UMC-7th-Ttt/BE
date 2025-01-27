package com.umc.ttt.domain.member.service;

import com.umc.ttt.domain.member.dto.MemberSignUpDTO;
import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.member.entity.enums.ProviderType;
import com.umc.ttt.domain.member.entity.enums.Role;
import com.umc.ttt.domain.member.repository.MemberRepository;
import com.umc.ttt.global.apiPayload.code.status.ErrorStatus;
import com.umc.ttt.global.apiPayload.exception.GeneralException;
import com.umc.ttt.global.jwt.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public void signUp(MemberSignUpDTO memberSignUpDto) throws Exception {
        Optional<Member> optionalMember = memberRepository.findByEmail(memberSignUpDto.getEmail());

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            if (member.getProviderType() == ProviderType.GOOGLE) {
                throw new Exception("이미 존재하는 이메일입니다. 구글 로그인으로 로그인해주세요.");
            } else {
                throw new Exception("이미 존재하는 이메일입니다.");
            }
        }

        if (memberRepository.findByNickname(memberSignUpDto.getNickname()).isPresent()) {
            throw new Exception("이미 존재하는 닉네임입니다.");
        }

        Member member = Member.builder()
                .email(memberSignUpDto.getEmail())
                .password(memberSignUpDto.getPassword())
                .nickname(memberSignUpDto.getNickname())
                .profileUrl(memberSignUpDto.getProfileUrl())
                .providerType(ProviderType.EMAIL)
                .role(Role.GUEST)
                .build();

        member.passwordEncode(passwordEncoder);
        memberRepository.save(member);
    }

    @Override
    public void signOut(Optional<String> userEmail) throws Exception {
        log.info("회원탈퇴 이메일 : {}", userEmail.orElse("이메일 없음"));

        String email = userEmail.get();
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            memberRepository.deleteById(member.get().getId());
        } else {
            throw new GeneralException(ErrorStatus.MEMBER_NOT_FOUND);
        }
    }


    @Override
    public void logout(HttpServletRequest request) throws Exception {
        jwtService.extractAccessToken(request) // 요청에서 Access Token 추출
                .filter(jwtService::isTokenValid) // 유효한 토큰인지 확인
                .ifPresent(accessToken -> jwtService.extractEmail(accessToken) // 이메일 추출
                        .ifPresent(email -> memberRepository.findByEmail(email) // 이메일로 사용자 조회
                                .ifPresent(member -> {
                                    // member의 refreshToken 필드 비우기
                                    member.updateRefreshToken(null);

                                    // 변경사항 저장
                                    memberRepository.save(member);
                                })));

    }
}
