package com.umc.ttt.domain.member.converter;

import com.umc.ttt.domain.member.dto.TokenResponseDTO;

public class MemberConverter {
    public static TokenResponseDTO.UpdateResultDTO updateResultDTO(String token) {
        return TokenResponseDTO.UpdateResultDTO.builder()
                .accessToken(token)
                .build();
    }

}
