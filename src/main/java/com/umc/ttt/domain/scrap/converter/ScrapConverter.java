package com.umc.ttt.domain.scrap.converter;

import com.umc.ttt.domain.book.entity.Book;
import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.place.entity.Place;
import com.umc.ttt.domain.scrap.dto.ScrapResponseDTO;
import com.umc.ttt.domain.scrap.entity.BookScrap;
import com.umc.ttt.domain.scrap.entity.PlaceScrap;
import com.umc.ttt.domain.scrap.entity.ScrapFolder;

import java.util.List;
import java.util.stream.Collectors;

public class ScrapConverter {

    public static ScrapResponseDTO.PlaceScrapDTO toPlaceScrapDTO(Place place, Member member, boolean isScraped) {
        return ScrapResponseDTO.PlaceScrapDTO.builder()
                .memberId(member.getId())
                .placeId(place.getId())
                .isScraped(isScraped)
                .build();
    }

    public static ScrapResponseDTO.BookScrapDTO toBookScrapDTO(Book book, Member member, boolean isScraped) {
        return ScrapResponseDTO.BookScrapDTO.builder()
                .memberId(member.getId())
                .bookId(book.getId())
                .isScraped(isScraped)
                .build();
    }

    public static ScrapResponseDTO.ScrapFolderDTO toScrapFolderDTO(ScrapFolder scrapFolder) {
        return new ScrapResponseDTO.ScrapFolderDTO(
                scrapFolder.getId(),
                scrapFolder.getName()
        );
    }

    public static List<ScrapResponseDTO.ScrapFolderDTO> toScrapFolderDTOList(List<ScrapFolder> scrapFolders) {
        return scrapFolders.stream()
                .map(ScrapConverter::toScrapFolderDTO)
                .collect(Collectors.toList());
    }

    // 폴더의 스크랩 내역 조회
    public static ScrapResponseDTO.ScrapDTO toScrapDTO(BookScrap bookScrap) {
        return ScrapResponseDTO.ScrapDTO.builder()
                .id(bookScrap.getId())
                .title(bookScrap.getBook().getTitle())
                .authorOrAddress(bookScrap.getBook().getAuthor())
                .image(bookScrap.getBook().getCover())
                .type("BOOK")
                .createdAt(bookScrap.getCreatedAt())
                .build();
    }

    public static ScrapResponseDTO.ScrapDTO toScrapDTO(PlaceScrap placeScrap) {
        return ScrapResponseDTO.ScrapDTO.builder()
                .id(placeScrap.getId())
                .title(placeScrap.getPlace().getTitle())
                .authorOrAddress(placeScrap.getPlace().getAddress())
                .image(placeScrap.getPlace().getImage())
                .type("PLACE")
                .createdAt(placeScrap.getCreatedAt())
                .build();
    }

    public static ScrapResponseDTO.ScrapListDTO toScrapListDTO(List<ScrapResponseDTO.ScrapDTO> scraps, Long nextBookCursor, Long nextPlaceCursor, int limit, boolean hasNext) {
        return ScrapResponseDTO.ScrapListDTO.builder()
                .scraps(scraps)
                .nextBookCursor(nextBookCursor)
                .nextPlaceCursor(nextPlaceCursor)
                .limit(limit)
                .hasNext(hasNext)
                .build();
    }
}
