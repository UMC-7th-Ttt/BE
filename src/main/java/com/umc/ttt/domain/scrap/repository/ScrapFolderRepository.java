package com.umc.ttt.domain.scrap.repository;

import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.scrap.entity.ScrapFolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScrapFolderRepository extends JpaRepository<ScrapFolder, Long> {
    List<ScrapFolder> findAllByMember(Member member);

    Optional<ScrapFolder> findByMemberAndName(Member member, String folder);
}
