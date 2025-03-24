package com.zdatai.finverus.annotation;

import com.zdatai.finverus.config.FieldValidator;
import com.zdatai.finverus.enums.PatternEnum;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldValidator.class)
public @interface ValidateField {
    String message() default "";

    boolean required() default false;

    int minLength() default 0;
    int maxLength() default Integer.MAX_VALUE;

    String pattern() default "";

    double minValue() default Double.MIN_VALUE;
    double maxValue() default Double.MAX_VALUE;

    int decimalPlaces() default -1;

    boolean mustBeInteger() default false;

    PatternEnum datePattern() default PatternEnum.CUSTOM;

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    Class<?> expectedType();
}

