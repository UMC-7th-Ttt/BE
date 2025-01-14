package com.umc.ttt.domain.place.repository;

import com.umc.ttt.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}