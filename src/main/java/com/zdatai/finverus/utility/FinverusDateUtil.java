package com.zdatai.finverus.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class FinverusDateUtil {
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String dateToString(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format != null ? format : DEFAULT_FORMAT);
        return sdf.format(date);
    }

    public static Date stringToDate(String dateString, String format) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format != null ? format : DEFAULT_FORMAT);

            if (format.equals("yyyy-MM-dd")) {
                LocalDate localDate = LocalDate.parse(dateString, formatter);
                return Date.from(localDate.atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
            }
            if (format.equals("yyyy-MM-dd'T'HH:mm:ss.SSS")) {
                LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
                return Date.from(dateTime.atZone(java.time.ZoneId.of("UTC")).toInstant());
            }
            if (format.equals("yyyy-MM-dd'T'HH:mm:ss.SSZ")) {
                OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                return Date.from(offsetDateTime.toInstant());
            }

            if (format.equals("yyyy-MM-dd'T'HH:mm:ssX") || format.equals("yyyy-MM-dd'T'HH:mm:ss.SSX")) {
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, DateTimeFormatter.ISO_ZONED_DATE_TIME);
                return Date.from(zonedDateTime.toInstant());
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }
}

