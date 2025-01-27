package com.umc.ttt.domain.scrap.repository;

import com.umc.ttt.domain.book.entity.Book;
import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.scrap.entity.BookScrap;
import com.umc.ttt.domain.scrap.entity.ScrapFolder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookScrapRepository extends JpaRepository<BookScrap, Long> {
    boolean existsByBook(Book book);

    Optional<BookScrap> findByBook(Book place);

    void deleteAllByScrapFolder(ScrapFolder scrapFolder);

    @Query("SELECT bs FROM BookScrap bs WHERE bs.scrapFolder.id = :folderId " +
            "AND (:cursor = 0 OR bs.id < :cursor) ORDER BY bs.id DESC ")
    List<BookScrap> findBookScrapsWithCursor(@Param("folderId") Long folderId, @Param("cursor") Long cursor, Pageable pageable);

    @Query("SELECT bs.book.id FROM BookScrap bs WHERE bs.scrapFolder.member = :member AND bs.book IN :books")
    List<Long> findScrapedBookIdsByMemberAndBooks(@Param("member") Member member, @Param("books") List<Book> books);

    boolean existsByScrapFolderMemberAndBook(Member member, Book book);

    List<BookScrap> findAllByIdInAndScrapFolder(List<Long> list, ScrapFolder destinationFolder);
}
