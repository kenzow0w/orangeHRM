package configs.login;

import configs.AbstractConfig;
import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:src/test/resources/accounts/appleProfile.properties"
})
public interface LoginAppleConfig extends AbstractConfig {

    @Key("name")
    String name();

    @Key("login")
    String login();

    @Key("password")
    String password();
}
