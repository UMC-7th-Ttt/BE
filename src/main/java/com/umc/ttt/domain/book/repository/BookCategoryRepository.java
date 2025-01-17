package com.umc.ttt.domain.book.repository;

import com.umc.ttt.domain.book.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
    boolean existsByCategoryName(String categoryName);
}
