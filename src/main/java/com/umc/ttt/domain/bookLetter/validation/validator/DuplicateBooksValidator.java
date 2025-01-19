package com.umc.ttt.domain.bookLetter.validation.validator;

import com.umc.ttt.domain.bookLetter.validation.annotataion.DuplicateBooks;
import com.umc.ttt.global.apiPayload.code.status.ErrorStatus;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DuplicateBooksValidator implements ConstraintValidator<DuplicateBooks, List<Long>> {

    @Override
    public void initialize(DuplicateBooks constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> bookIdList, ConstraintValidatorContext context) {
        boolean duplicate = bookIdList.stream()
                .anyMatch(bookId -> Collections.frequency(bookIdList, bookId) > 1);

        if(duplicate){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.DUPLICATE_BOOK.toString()).addConstraintViolation();
        }

        return !duplicate;
    }
}
