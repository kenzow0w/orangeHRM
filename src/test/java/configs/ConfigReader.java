package configs;

import configs.login.LoginAppleConfig;
import configs.login.LoginOrangeConfig;
import org.aeonbits.owner.ConfigFactory;

import java.util.ArrayList;
import java.util.List;

public class ConfigReader {
    public static final List<AbstractConfig> abstractConfigs = new ArrayList<>();
    public static final LoginOrangeConfig LOGIN_ORANGE_CONFIG = ConfigFactory.create(LoginOrangeConfig.class, System.getProperties());
    public static final LoginAppleConfig LOGIN_APPLE_CONFIG = ConfigFactory.create(LoginAppleConfig.class, System.getProperties());

    public static List<AbstractConfig> getLoginConfigs() {
        abstractConfigs.add(LOGIN_APPLE_CONFIG);
        abstractConfigs.add(LOGIN_ORANGE_CONFIG);
        return abstractConfigs;
    }


}
