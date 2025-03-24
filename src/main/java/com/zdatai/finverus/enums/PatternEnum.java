package com.zdatai.finverus.enums;

import lombok.Getter;

@Getter
public enum PatternEnum {
    DATE_YYYY_MM_DD("^\\d{4}-\\d{2}-\\d{2}$", "yyyy-MM-dd"),
    DATE_YYYY_MM_DD_HH_MM_SSS("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}$", "yyyy-MM-dd'T'HH:mm:ss.SSS"),
    DATE_YYYY_MM_DD_HH_MM_SSSX("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}[Z±]\\d{2}:\\d{2}$", "yyyy-MM-dd'T'HH:mm:ss.SSSX"),
    DATE_YYYY_MM_DD_HH_MM_SSZ("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z$", "yyyy-MM-dd'T'HH:mm:ssZ"),
    INTEGER("^-?\\d+$", "[1,9]"),
    DECIMAL("^\\d+(\\.\\d+)?$", "0.00"),
    CUSTOM("", ""),
    DATE_YYYY_MM_DD_HH_MM_SSX("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}[Z±]\\d{2}:\\d{2}$", "yyyy-MM-dd'T'HH:mm:ss.SSX");

    private final String pattern;
    private final String description;

    PatternEnum(String pattern, String description) {
        this.pattern = pattern;
        this.description = description;
    }

    public static String numberRangePattern(double min, double max) {
        return "^(?:" + min + "(?:\\.\\d+)?|(?:\\d{1,})|(?:" + max + "(?:\\.\\d+)?))$";
    }

    public static String fixedDigitPattern(int minDigits, int maxDigits) {
        return "^[0-9]{" + minDigits + "," + maxDigits + "}$";
    }

    public static String decimalPlacesPattern(int decimalPlaces) {
        return "^\\d+(\\.\\d{1," + decimalPlaces + "})?$";
    }
}

