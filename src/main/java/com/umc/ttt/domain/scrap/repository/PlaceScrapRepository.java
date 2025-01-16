package com.umc.ttt.domain.scrap.repository;

import com.umc.ttt.domain.place.entity.Place;
import com.umc.ttt.domain.scrap.entity.PlaceScrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceScrapRepository extends JpaRepository<PlaceScrap, Long> {
    boolean existsByPlace(Place place);

    Optional<PlaceScrap> findByPlace(Place place);
}
