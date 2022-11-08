package fields;

import com.codeborne.selenide.SelenideElement;
import factories.WebDriverFactory;
import org.openqa.selenium.By;
import service.AbstractSeleniumPage;

public class AbstractField {

    protected SelenideElement selenideElement;
    protected final String name;
    private String className;
    protected String xpathPath;

    public AbstractField(SelenideElement selenideElement, String name) {
        this.selenideElement = selenideElement;
        this.name = name;
    }

    public int quantityXpath(){
        int value = WebDriverFactory.getCurrentWebDriver().findElement(By.xpath(xpathPath)).getSize().height;
        return value;
    }


}
