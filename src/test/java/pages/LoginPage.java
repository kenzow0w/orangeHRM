package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import service.AbstractSeleniumPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.AccountCredentials;

public class LoginPage extends AbstractSeleniumPage {

    private static final Logger LOG = LogManager.getLogger(LoginPage.class);

    public LoginPage() {
        PageFactory.initElements(webDriver, this);
        boolean isOpen = shouldExistQuestion();
        Assertions.assertTrue(isOpen, "Login page is not Open");
    }

    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class, 'login-forgot')]")
    private WebElement questionAboutPassword;

    public LoginPage autorization(String username) throws InterruptedException {
        AccountCredentials accountCredentials = new AccountCredentials(username);
        AbstractSeleniumPage.sendKeysInElement(usernameField, accountCredentials.getLogin());
        AbstractSeleniumPage.sendKeysInElement(passwordField, accountCredentials.getPassword());
        clickLogin();
        return this;
    }

    public boolean shouldExistQuestion() {
        boolean flag = false;
        if (questionAboutPassword.getText().equals("Forgot your password?")) {
            flag = true;
        }
        return flag;
    }

    public void clickLogin() throws InterruptedException {
        AbstractSeleniumPage.clickButton(loginButton);
    }

}
