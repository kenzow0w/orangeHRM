package pages;

import factories.FieldFactory;
import factories.WebDriverFactory;
import fields.AbstractField;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import service.AbstractSeleniumPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.AccountCredentials;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginPage extends AbstractSeleniumPage {

    private static final Logger LOG = LogManager.getLogger(LoginPage.class);

    public LoginPage() {
        PageFactory.initElements(WebDriverFactory.getCurrentWebDriver(), this);
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

    public void clickLogin() throws InterruptedException {
        AbstractSeleniumPage.clickButton(loginButton);
    }

    @Test
    public void getNameFromAnnotation() {
//        List<Field> list = List.of(this.getClass().getDeclaredFields());
//        for(Field field : list){
//            if(field.isAnnotationPresent(FindBy.class)) {
//                System.out.println(field.getAnnotation(FindBy.class).xpath());
//                System.out.println(field.getName());
//            }
//        }
        List<Field> fieldFromPage = new ArrayList<>(FieldFactory.getPageFields.apply(LoginPage.class));
    }


}
