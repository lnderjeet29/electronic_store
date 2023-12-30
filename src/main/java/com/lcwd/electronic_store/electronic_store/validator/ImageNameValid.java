package com.lcwd.electronic_store.electronic_store.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidation.class)
public @interface ImageNameValid {
    String message() default "please enter the valid image...";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
