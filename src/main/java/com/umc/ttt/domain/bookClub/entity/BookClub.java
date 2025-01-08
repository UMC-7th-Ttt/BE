package com.umc.ttt.domain.bookClub.entity;

import com.umc.ttt.domain.bookLetter.entity.BookLetter;
import com.umc.ttt.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class BookClub extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_club_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDate startDate;    // 활동 시작 날짜

    @Column(nullable = false)
    private LocalDate endDate;    // 활동 종료 날짜

    @Column(nullable = false)
    private String comment; // 한줄 추천 멘트

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_letter_id")
    private BookLetter bookLetter;
}
