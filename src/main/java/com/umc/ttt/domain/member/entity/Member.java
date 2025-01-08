package com.umc.ttt.domain.member.entity;

import com.umc.ttt.domain.member.entity.enums.ProviderType;
import com.umc.ttt.domain.member.entity.enums.Role;
import com.umc.ttt.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String email;   // 이메일

    private String password;    // 비밀번호

    private String nickname;    // 닉네임

    private String profileUrl;  // 프로필 이미지

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;  // 역할(USER, ADMIN)

    @Enumerated(EnumType.STRING)
    private ProviderType providerType;  // 로그인 타입(EMAIL, GOOGLE)
}
