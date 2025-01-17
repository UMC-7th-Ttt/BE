package com.umc.ttt.domain.member.repository;

import com.umc.ttt.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
