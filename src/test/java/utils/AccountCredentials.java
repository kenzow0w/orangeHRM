package utils;

import configs.AbstractConfig;
import configs.ConfigReader;
import configs.login.LoginAppleConfig;
import exceptions.AutotestException;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static configs.ConfigReader.abstractConfigs;

@Getter
@Setter
public class AccountCredentials {

    protected static final Logger LOG = LogManager.getLogger(AccountCredentials.class);

    private final String login;
    private final String password;
    private final String name;

    private static AbstractConfig getConfig(String username) throws AutotestException {
        AbstractConfig resultCfg = null;
        if (username == null) {
            throw new RuntimeException("Invalid value");
        } else {
            for (AbstractConfig cfg : abstractConfigs) {
                if (cfg.name().equals(username)) {
                    resultCfg = cfg;
                }
            }
        }
        return resultCfg;
    }

    @SneakyThrows
    public AccountCredentials(String username) {
        try {
            name = getConfig(username).name();
            login = getConfig(username).login();
            password = getConfig(username).password();
            Evaluator.setVariable("login", login);
            Evaluator.setVariable("password", password);
            Evaluator.setVariable("name", name);
            LOG.info("Initialization user with credentials: name: [{}], user: [{}], password [{}]", name, login, password);
        } catch (RuntimeException e) {
            throw new AutotestException("Invalid username profile");
        }


    }


}
