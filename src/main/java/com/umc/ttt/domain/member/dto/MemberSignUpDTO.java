package com.umc.ttt.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignUpDTO {//자체 로그인 회원가입 API RequestBody / 키워드 나중에 추가할 것
    private String email;
    private String password;
    private String nickname;
    private String profileUrl;
}
