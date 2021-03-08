package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.Instant;
import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Answers.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mockStatic;

public class DateTimeUtilsTest {

    @Test
    public void testMillisTillTomorrow() {
        // shortly before Dec 25, 2021
        Instant fixedNow = LocalDateTime.of(2021, 12, 24, 23, 29, 40).toInstant(UTC);

        try (MockedStatic<Instant> mocked = mockStatic(Instant.class, CALLS_REAL_METHODS)) {
            mocked.when(() -> Instant.now())
                    .thenReturn(fixedNow);

            long expectedMillis = SECONDS.toMillis(20) + MINUTES.toMillis(30);
            assertThat(DateTimeUtils.millisTillTomorrow()).isEqualTo(expectedMillis);
        }
    }

}
