package com.zdatai.finverus.config;

import com.zdatai.finverus.annotation.ValidateField;
import com.zdatai.finverus.dto.InputField;
import com.zdatai.finverus.enums.PatternEnum;
import com.zdatai.finverus.exception.FinVerusException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;
import static com.zdatai.finverus.utility.FinverusDateUtil.stringToDate;

public class FieldValidator implements ConstraintValidator<ValidateField, InputField<?>> {
    private ValidateField annotation;

    @Override
    public void initialize(ValidateField annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(InputField<?> inputField, ConstraintValidatorContext context) {
        if (inputField == null || inputField.getValue() == null) {
            return !annotation.required();
        }

        String value = inputField.getValue();
        if (annotation.required() && value.isBlank()) {
            return buildViolation(context, getDefaultMessage(annotation.expectedType()));
        }

        if (!isValidLength(value) || !isValidPattern(value) || !isValidDatePattern(value) || !isValidNumber(value)) {
            return buildViolation(context, getDefaultMessage(annotation.expectedType()));
        }

        try {
            setTypedValue(inputField, value, annotation.expectedType());
        } catch (Exception e) {
            return buildViolation(context, "Invalid data type: " + e.getMessage());
        }

        return true;
    }

    private boolean isValidLength(String value) {
        return value.length() >= annotation.minLength() && value.length() <= annotation.maxLength();
    }

    private boolean isValidPattern(String value) {
        return annotation.pattern().isEmpty() || Pattern.matches(annotation.pattern(), value);
    }

    private boolean isValidDatePattern(String value) {
        if (annotation.datePattern() == PatternEnum.CUSTOM) return true;
        return Pattern.matches(annotation.datePattern().getPattern(), value);
    }

    private boolean isValidNumber(String value) {
        if (annotation.mustBeInteger() && !Pattern.matches(PatternEnum.INTEGER.getPattern(), value)) {
            return false;
        }
        if (annotation.minValue() != Double.MIN_VALUE || annotation.maxValue() != Double.MAX_VALUE) {
            return Pattern.matches(PatternEnum.numberRangePattern(annotation.minValue(), annotation.maxValue()), value);
        }
        return annotation.decimalPlaces() < 0 || Pattern.matches(
                PatternEnum.decimalPlacesPattern(annotation.decimalPlaces()), value);
    }

    private <T> void setTypedValue(InputField inputField, String value, Class<T> targetType) {
        inputField.setTypedValue(convertValue(value, targetType));
    }

    private <T> T convertValue(String value, Class<T> targetType) {
        if (targetType == Integer.class) return targetType.cast(Integer.valueOf(value));
        if (targetType == Long.class) return targetType.cast(Long.valueOf(value));
        if (targetType == Double.class) return targetType.cast(Double.valueOf(value));
        if (targetType == String.class) return targetType.cast(value);
        if (targetType == BigDecimal.class) return targetType.cast(new BigDecimal(value));
        if (targetType == LocalDate.class) return targetType.cast(parseLocalDate(value));
        if (targetType == LocalDateTime.class) return targetType.cast(parseLocalDateTime(value));
        if (targetType == Date.class) return targetType.cast(parseDate(value));

        throw new FinVerusException("Unsupported data type: " + targetType.getSimpleName());
    }

    private LocalDate parseLocalDate(String value) {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern(annotation.datePattern().getPattern()));
    }

    private LocalDateTime parseLocalDateTime(String value) {
        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(annotation.datePattern().getPattern()));
    }

    private Date parseDate(String value) {
        return stringToDate(value, annotation.datePattern().getDescription());
    }

    private String getDefaultMessage(Class<?> targetType) {
        if (!annotation.message().isEmpty()) {
            return annotation.message();
        }

        return switch (targetType.getSimpleName()) {
            case "String" -> "Invalid text format.";
            case "Short" -> "Invalid character format.";
            case "Integer", "Long" -> "Must be a valid number.";
            case "Double", "BigDecimal" -> "Must be a valid decimal number.";
            case "Date", "LocalDate", "LocalDateTime" -> "Must follow the date format "
                    + annotation.datePattern().getDescription();
            default -> "Validation Failed";
        };
    }

    private boolean buildViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return false;
    }
}
