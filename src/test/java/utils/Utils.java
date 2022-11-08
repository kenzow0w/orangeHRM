package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class Utils {

    protected static final Logger LOG = LogManager.getLogger(Utils.class);

    public static void freeze(long time) {
        Duration duration = Duration.ofMillis(time);
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            LOG.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
