package configs;

import org.aeonbits.owner.Config;

public interface AbstractConfig extends Config {
    String name();
    String login();
    String password();
}
