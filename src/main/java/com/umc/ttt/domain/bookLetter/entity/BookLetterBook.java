package com.umc.ttt.domain.bookLetter.entity;

import com.umc.ttt.domain.book.entity.Book;
import com.umc.ttt.domain.bookClub.entity.BookClub;
import com.umc.ttt.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"book_letter_id", "book_id"})
        }
)
public class BookLetterBook extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_letter_book_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_letter_id", nullable = false)
    private BookLetter bookLetter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id", nullable = false)
    private Book book;

    @OneToOne(mappedBy = "bookLetterBook", cascade = CascadeType.PERSIST, orphanRemoval = false)
    private BookClub bookClub;
}
