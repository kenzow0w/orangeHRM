package pages;

import factories.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import service.AbstractSeleniumPage;

public class LeftMenuPage extends AbstractSeleniumPage {
    public LeftMenuPage(){
        PageFactory.initElements(WebDriverFactory.getCurrentWebDriver(), this);
    }

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name' and text()='Directory']")
    private WebElement directoryButton;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name' and text()='Admin']")
    private WebElement adminButton;

    public void clickAdminButton() throws InterruptedException {
        AbstractSeleniumPage.clickButton(adminButton);
    }

    public MainPage clickDirectoryButton() throws InterruptedException {
        AbstractSeleniumPage.clickButton(directoryButton);
        return new MainPage();
    }
}
