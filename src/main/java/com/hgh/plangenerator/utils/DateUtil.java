package com.hgh.plangenerator.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String generatePaymentDate(String startDateTime) {
        final LocalDate startDate = convertStringToLocalDate(startDateTime);
        return convertLocalDateToString(startDate);
    }

    public static String generatePaymentDate(String startDateTime, int counter) {
        final LocalDate startDate = convertStringToLocalDate(startDateTime);
        final LocalDate paymentDate = startDate.plusMonths(counter);
        return convertLocalDateToString(paymentDate);
    }

    public static LocalDate convertStringToLocalDate(String dateTimeAsString) {
        final Instant instant = Instant.parse(dateTimeAsString);
        final LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
        return localDateTime.toLocalDate();
    }

    public static String convertLocalDateToString(LocalDate localDate) {
        final ZonedDateTime zonedDateTime = ZonedDateTime
                .of(LocalDateTime.of(localDate, LocalTime.MIDNIGHT), ZoneId.of("UTC"));
        return zonedDateTime.format(DateTimeFormatter.ISO_INSTANT);
    }
}
