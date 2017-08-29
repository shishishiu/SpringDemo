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
    Class<?>[] groups() default {};//引数に使う型を宣言
    Class<? extends Payload>[] payload() default {};

}
