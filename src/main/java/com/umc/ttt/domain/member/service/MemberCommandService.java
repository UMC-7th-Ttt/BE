package com.umc.ttt.domain.member.service;

import com.umc.ttt.domain.member.dto.MemberSignUpDTO;

public interface MemberCommandService {
    void signUp(MemberSignUpDTO memberSignUpDto) throws Exception;
}
