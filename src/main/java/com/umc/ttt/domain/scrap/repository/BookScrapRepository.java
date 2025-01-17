package com.umc.ttt.domain.scrap.repository;

import com.umc.ttt.domain.scrap.entity.BookScrap;
import com.umc.ttt.domain.scrap.entity.ScrapFolder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookScrapRepository extends JpaRepository<BookScrap, Long> {
    void deleteAllByScrapFolder(ScrapFolder scrapFolder);

    @Query("SELECT bs FROM BookScrap bs WHERE bs.scrapFolder.id = :folderId " +
            "AND (:cursor = 0 OR bs.id < :cursor) ORDER BY bs.id DESC ")
    List<BookScrap> findBookScrapsWithCursor(@Param("folderId") Long folderId, @Param("cursor") Long cursor, Pageable pageable);
}
