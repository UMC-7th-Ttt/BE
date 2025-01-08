package com.umc.ttt.domain.place.entity;

import com.umc.ttt.domain.place.entity.enums.PlaceCategory;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;   // 상호명

    @Column(nullable = false)
    private String issuedDate;   // 등록일

    @Column(nullable = false)
    private PlaceCategory category;    // 카테고리(BOOKSTORE, CAFE)

    @Column(nullable = false)
    private String address; // 주소

    @Column(nullable = false)
    private double xPos;    // 위도

    @Column(nullable = false)
    private double yPos;    // 경도

    private String holiday; // 휴무일

    private String weekdaysBusiness;    // 평일 영업시간

    private String satBusiness; // 토요일 영업시간

    private String sunBusiness; // 일요일 영업시간

    @Column(nullable = false)
    private String phone;   // 전화번호

    @Column(nullable = false)
    private boolean hasParking;    // 주차 가능 여부

    @Column(nullable = false)
    private boolean hasCafe;  // 카페 여부

    @Column(nullable = false)
    private boolean hasSpaceRental; // 공간 대여 여부

    private String image;   // 장소 이미지

    private double rating;  // 장소 평균 평점(계산)

    private String curation;    // 큐레이션
}
