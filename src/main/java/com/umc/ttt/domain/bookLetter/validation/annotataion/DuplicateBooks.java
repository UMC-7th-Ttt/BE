package com.umc.ttt.domain.bookLetter.validation.annotataion;

import com.umc.ttt.domain.bookLetter.validation.validator.DuplicateBooksValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DuplicateBooksValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface DuplicateBooks {
    String message() default "한 북레터에서 책은 중복될 수 없습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
