package com.umc.ttt.global.config;

import com.umc.ttt.domain.book.entity.BookCategory;
import com.umc.ttt.domain.book.repository.BookCategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BookCategoryInitializer {

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @PostConstruct
    public void initializeCategories() {
        List<String> defaultCategories = Arrays.asList(
                "과학", "여행에세이", "외국문학", "시", "장르소설",
                "자기계발", "인문", "소설", "판타지", "미스터리",
                "고전", "성장", "심리학", "비즈니스", "역사",
                "논리", "로맨스", "감성", "힐링", "종교학",
                "문화", "철학", "예술"
        );

        for (String categoryName : defaultCategories) {
            if (!bookCategoryRepository.existsByCategoryName(categoryName)) {
                BookCategory category = BookCategory.builder()
                        .categoryName(categoryName)
                        .build();
                bookCategoryRepository.save(category);
            }
        }
    }
}