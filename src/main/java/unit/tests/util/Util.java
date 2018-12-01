package unit.tests.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Util {
    public static Instant getNowToValidadePassword() {
        return Instant.now().minus(91, ChronoUnit.DAYS);
    }
}
