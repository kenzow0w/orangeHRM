package pages;

import exceptions.AutotestException;
import factories.WebDriverFactory;
import lombok.SneakyThrows;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import service.AbstractSeleniumPage;

import java.util.List;

public class LeftMenuPage extends AbstractSeleniumPage {
    public LeftMenuPage() {
        PageFactory.initElements(WebDriverFactory.getCurrentWebDriver(), this);
    }

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name']")
    private List<WebElement> menuButton;

    @SneakyThrows
    public void clickButtonFromMenuForName(String nameButton) {
        try {
            for (WebElement element : menuButton) {
                if (element.getText().equals(nameButton)) {
                    AbstractSeleniumPage.clickButton(element);
                    break;
                }
            }
        } catch (RuntimeException e) {
            throw new AutotestException(String.format("Element [%s] doesn't exist on the page", nameButton));
        }
    }
}
