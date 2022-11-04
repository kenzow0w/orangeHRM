package service;

import com.codeborne.selenide.Selenide;
import configs.ConfigReader;
import exceptions.AutotestException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

abstract public class AbstractSeleniumTest {

    protected static final Logger LOG = LogManager.getLogger(AbstractSeleniumTest.class);
    protected WebDriver driver;
    protected final String URL = "https://opensource-demo.orangehrmlive.com/";
    protected final String USERNAME = "Admin";
    protected final String PASS = "admin123";

    @BeforeTest
    public void setUp() throws AutotestException, InterruptedException {
        ConfigReader.getLoginConfigs();
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = chromeDriverOptionsInit();

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        AbstractSeleniumPage.setWebDriver(driver);
        AbstractSeleniumPage.openWebSite(URL);
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

    public void clearCookiesAndLocalStorage() {
        try {
//            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
//            javascriptExecutor.executeScript("window.sessionStorage.clear()");
        } catch (Exception e) {
            throw e;
        }
    }

    @AfterTest()
    public void tearDown() {
        clearCookiesAndLocalStorage();
    }

    @AfterSuite(alwaysRun = true)
    public void close() {
        if (driver != null) {
            Selenide.closeWebDriver();
            driver.quit();
        }
    }
}
