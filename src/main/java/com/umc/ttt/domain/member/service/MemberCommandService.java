package com.umc.ttt.domain.member.service;

import com.umc.ttt.domain.member.dto.MemberSignUpDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface MemberCommandService {
    void signUp(MemberSignUpDTO memberSignUpDto) throws Exception;
    void logout(HttpServletRequest request) throws Exception;

}
