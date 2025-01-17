package com.umc.ttt.domain.scrap.entity;

import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class ScrapFolder extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_folder_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "scrapFolder", cascade = CascadeType.ALL)
    private List<BookScrap> bookScraps = new ArrayList<>();

    @OneToMany(mappedBy = "scrapFolder", cascade = CascadeType.ALL)
    private List<PlaceScrap> placeScraps = new ArrayList<>();

    // 기본 폴더 여부
    public boolean isDefaultFolder() {
        return this.name.equals("도서") || this.name.equals("공간");
    }
}
