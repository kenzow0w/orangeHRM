package service;

import exceptions.AutotestException;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static factories.WebDriverFactory.*;
import static utils.Utils.freeze;

abstract public class AbstractSeleniumPage {

    protected static final Logger LOG = LogManager.getLogger(AbstractSeleniumPage.class);


    public static void getWebSite(String url) throws InterruptedException, AutotestException {
        int count = 0;
        do {
            try {
                if (count == 4) {
                    throw new AutotestException("Page is not open");
                }
                count++;
                LOG.info("Attempt " + count + " open URL browser");
                getCurrentWebDriver().get(url);
                break;
            } catch (RuntimeException e) {
                freeze(3000);
            }
        } while (count < 3);
    }

    public static void refreshPage() {
        getCurrentWebDriver().navigate().refresh();
    }

    public static void clickButton(WebElement element) throws InterruptedException {
        int tries = 0;
        do {
            try {
                if (tries == 4) {
                    Assertions.assertTrue(false, "error");
                }
                tries++;
                LOG.info("Click Attempt #" + tries);
                element.click();
                break;
            } catch (RuntimeException e) {
                freeze(1000);
            }
        } while (tries < 4);
    }

    public static void sendKeysInElement(WebElement element, String text) {
        element.sendKeys(text);
    }

    public static void scrollIntoView(String to) {
        JavascriptExecutor executor = (JavascriptExecutor) getCurrentWebDriver();
        switch (to) {
            case "down":
                executor.executeScript("window.scrollBy(0,200)");
                break;
            case "up":
                executor.executeScript("window.scrollBy(0,-300)");
                break;
        }
    }
}
