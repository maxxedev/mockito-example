package com.example;

import java.time.Duration;
import java.time.Instant;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateTimeUtils {

    public static long millisTillTomorrow() {
        Instant now = Instant.now();
        Instant tomorrow = now.plus(1, DAYS).truncatedTo(DAYS);
        return Duration.between(now, tomorrow).toMillis();
    }
}
