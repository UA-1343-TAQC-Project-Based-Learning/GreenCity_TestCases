package com.greencity.api.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final SimpleDateFormat PARSER =
            new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String convertRawDateToIso(String rawDate) throws ParseException {
        Date parsedDate = PARSER.parse(rawDate);
        LocalDate localDate = parsedDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return localDate.format(FORMATTER);
    }
}
