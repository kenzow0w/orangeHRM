package listeners.highlighter;

import listeners.WebDriverEventHandler;
import lombok.SneakyThrows;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import service.AbstractSeleniumPage;

import static utils.Utils.freeze;

public class HighLighter {

    @SneakyThrows
    public static void highLightElement(WebDriver webdriver, WebElement element){
        ((JavascriptExecutor)webdriver).executeScript("arguments[0].style.border='3px solid red'", element);
        freeze(300);
        ((JavascriptExecutor)webdriver).executeScript("arguments[0].style.border=''", element);

    }

}
