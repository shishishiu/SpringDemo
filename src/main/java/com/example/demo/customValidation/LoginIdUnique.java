package com.example.demo.customValidation;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE,ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=LoginIdValidator.class)
@Documented
public @interface LoginIdUnique {
    String message() default ""; //error message
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String fieldLoginId() default "loginId";
    String fieldHidId() default "hidId";
}
