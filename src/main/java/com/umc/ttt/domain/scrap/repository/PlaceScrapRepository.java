package com.umc.ttt.domain.scrap.repository;

import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.place.entity.Place;
import com.umc.ttt.domain.scrap.entity.PlaceScrap;
import com.umc.ttt.domain.scrap.entity.ScrapFolder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlaceScrapRepository extends JpaRepository<PlaceScrap, Long> {
    boolean existsByPlace(Place place);

    Optional<PlaceScrap> findByPlace(Place place);

    void deleteAllByScrapFolder(ScrapFolder scrapFolder);

    @Query("SELECT ps FROM PlaceScrap ps WHERE ps.scrapFolder.id = :folderId " +
            "AND (:cursor = 0 OR ps.id < :cursor) ORDER BY ps.id DESC ")
    List<PlaceScrap> findPlaceScrapsWithCursor(@Param("folderId") Long folderId, @Param("cursor") Long cursor, Pageable pageable);

    @Query("SELECT ps.place.id FROM PlaceScrap ps WHERE ps.scrapFolder.member = :member AND ps.place IN :places")
    List<Long> findScrapedPlaceIdsByMemberAndPlaces(@Param("member") Member member, @Param("places") List<Place> places);

    boolean existsByScrapFolderMemberAndPlace(Member member, Place place);
}
