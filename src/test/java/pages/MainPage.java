package pages;

import factories.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.AbstractSeleniumPage;

import java.time.Duration;

public class MainPage extends AbstractSeleniumPage {

    public MainPage() {
        PageFactory.initElements(WebDriverFactory.getCurrentWebDriver(), this);
    }

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement employeesNameField;

    @FindBy(xpath = "//*[text()='Location']/following::div[@class='oxd-select-wrapper']")
    private WebElement locationField;

    @FindBy(xpath = "(//div[@role='listbox'])[1]")
    private WebElement locationUSA;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
    private WebElement profileIcon;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logout;

    @FindBy(xpath = "//span[@class='oxd-topbar-body-nav-tab-item' and contains(text(), 'Job')]")
    private WebElement listJob;

    @FindBy(xpath = "(//ul[@class='oxd-dropdown-menu']/li/a)[2]")
    private WebElement listJob2;

    public MainPage fillFieldWithValue(String text) {
        AbstractSeleniumPage.sendKeysInElement(employeesNameField, text);
        return this;
    }

    public MainPage clickLocationField() throws InterruptedException {
        AbstractSeleniumPage.clickButton(locationField);
        return this;
    }

    public MainPage chooseOneOfLocation() throws InterruptedException {
        locationUSA.click();
        return this;
    }

    public void clickSearchButton() throws InterruptedException {
        AbstractSeleniumPage.clickButton(searchButton);
    }

    public void logout() throws InterruptedException {
        AbstractSeleniumPage.clickButton(profileIcon);
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getCurrentWebDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
    }

    public MainPage clickElementForNameFromJobList(String name) throws InterruptedException {
        try{
            listJob2.click();
        }catch (RuntimeException e){
            throw new RuntimeException("exception");
        }

        return this;
    }

    public MainPage clickJobButton() throws InterruptedException {
        AbstractSeleniumPage.clickButton(listJob);
        return this;
    }
}
