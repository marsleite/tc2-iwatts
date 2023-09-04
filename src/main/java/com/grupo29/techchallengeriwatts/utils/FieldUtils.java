package com.grupo29.techchallengeriwatts.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

public class FieldUtils {

    private FieldUtils() {
    }

    public static Boolean areFieldsNotNull(Object... fields) {
        return Arrays.stream(fields).allMatch(Objects::nonNull);
    }

    public static LocalDate convertStringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }
}
