package com.umc.ttt.domain.member.service;

import com.umc.ttt.domain.member.dto.MemberSignUpDTO;
import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.member.entity.enums.Role;
import com.umc.ttt.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(MemberSignUpDTO memberSignUpDto) throws Exception {

        if (memberRepository.findByEmail(memberSignUpDto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        if (memberRepository.findByNickname(memberSignUpDto.getNickname()).isPresent()) {
            throw new Exception("이미 존재하는 닉네임입니다.");
        }

        Member member = Member.builder()
                .email(memberSignUpDto.getEmail())
                .password(memberSignUpDto.getPassword())
                .nickname(memberSignUpDto.getNickname())
                .profileUrl(memberSignUpDto.getProfileUrl())
                .role(Role.USER)
                .build();

        member.passwordEncode(passwordEncoder);
        memberRepository.save(member);
    }
}
