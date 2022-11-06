package service;

import com.codeborne.selenide.Selenide;
import configs.ConfigReader;
import exceptions.AutotestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import static factories.WebDriverFactory.*;

abstract public class AbstractSeleniumTest {

    protected static final Logger LOG = LogManager.getLogger(AbstractSeleniumTest.class);
    protected final String URL = ConfigReader.PROPERTY_CONFIG.url();

    @BeforeTest
    public void setUp() throws AutotestException, InterruptedException {
        ConfigReader.getLoginConfigs();
        createDriver();
        AbstractSeleniumPage.getWebSite(URL);
        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> getCurrentWebDriver().quit()
        ));
    }

    public void clearCookiesAndLocalStorage() {
        try {
//            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            getCurrentWebDriver().manage().deleteAllCookies();
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
        if (getCurrentWebDriver() != null) {
            Selenide.closeWebDriver();
            getCurrentWebDriver().quit();
        }
    }
}
