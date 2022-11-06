package factories;

import configs.ConfigReader;
import exceptions.AutotestException;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.WebDriverEventHandler;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WebDriverFactory {

    private static final Logger LOG = LogManager.getLogger(WebDriverFactory.class);
    public static WebDriver currentDriver;
    private static String currentDriverName;
    private static HashMap<String, WebDriver> drivers = new HashMap<>();
    private static final String browser = ConfigReader.PROPERTY_CONFIG.browser();
    private static final int WAITING_SHORT_TIMEOUT = ConfigReader.PROPERTY_CONFIG.timeoutShort();
    private final int WAITING_LONG_TIMEOUT = ConfigReader.PROPERTY_CONFIG.timeoutLong();

    public static void setCurrentWebDriver(WebDriver driver) {
        currentDriver = driver;
    }

    public static WebDriver getCurrentWebDriver() {
        return currentDriver;
    }

    public static WebDriver createDriver() {
        String driverUniqueName = UUID.randomUUID().toString();
        LOG.info("Started driver with name {}, to change the name using .setCurrentDriverName(String)", driverUniqueName);
        currentDriverName = driverUniqueName;
        return initDriver();
    }

    public static void setCurrentDriverName(String driverName) {
        drivers.put(driverName, drivers.remove(currentDriverName));
        currentDriverName = driverName;
    }


    @SneakyThrows
    public static WebDriver initDriver() {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = chromeDriverOptionsInit();
                currentDriver = new ChromeDriver(options);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = firefoxDriverOptionsInit();
                currentDriver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = edgeDriverOptionsInit();
                currentDriver = new EdgeDriver(edgeOptions);
                break;
            default:
                currentDriver = null;
                throw new AutotestException("Invalid value driver name");
        }
        setCurrentWebDriver(WebDriverEventHandler.registerWebDriverEventListener(currentDriver));
        currentDriver.manage().window().maximize();
        currentDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(WAITING_SHORT_TIMEOUT));
        currentDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAITING_SHORT_TIMEOUT));
        drivers.put(currentDriverName, currentDriver);
        return currentDriver;
    }

    private static ChromeOptions chromeDriverOptionsInit() {
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        return chromeOptions;
    }

    private static FirefoxOptions firefoxDriverOptionsInit() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        firefoxOptions.setCapability("prefs", prefs);
        firefoxOptions.setCapability("excludeSwitches", new String[]{"enable-automation"});
        return firefoxOptions;
    }

    private static EdgeOptions edgeDriverOptionsInit() {
        EdgeOptions edgeOptions = new EdgeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        edgeOptions.setExperimentalOption("prefs", prefs);
        edgeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        return edgeOptions;
    }


}
