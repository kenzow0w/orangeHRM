package pages;

import factories.WebDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import service.AbstractSeleniumPage;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PayGradesPage extends AbstractSeleniumPage {

    public PayGradesPage() {
        PageFactory.initElements(WebDriverFactory.getCurrentWebDriver(), this);
    }

    @FindBy(xpath = "//i[@class=\'oxd-icon bi-plus oxd-button-icon\']")
    private WebElement addPayGradeButton;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")
    private WebElement addCurrenciesButton;

    @FindBy(xpath = "//div/input[@class='oxd-input oxd-input--active' and not(@placeholder = 'Search')]")
    private WebElement nameField;

    @FindBy(xpath = "(//button[@type='submit'])[1]")
    private WebElement savePGButton;

    @FindBy(xpath = "(//button[@type='submit'])[2]")
    private WebElement saveCurrencyButton;

    @FindBy(xpath = "//div[@class='oxd-select-text oxd-select-text--active']")
    private WebElement selectButton;

    @FindBy(xpath = "(//div[contains(@class, 'success')])[1]")
    private WebElement successMessage;

    @FindBy(xpath = "//label[text()='Minimum Salary']/../following-sibling::div/input")
    private WebElement minimumSalary;

    @FindBy(xpath = "//label[text()='Maximum Salary']/../following-sibling::div/input")
    private WebElement maximumSalary;

    @FindBy(xpath = "//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    private WebElement textH6;

    @FindBy(xpath = "(//p[@class='oxd-text oxd-text--p orangehrm-form-hint'])[last()]")
    private WebElement requiredText;


    public PayGradesPage clickAddPGButton() throws InterruptedException {
        AbstractSeleniumPage.clickButton(addPayGradeButton);
        return this;
    }

    public PayGradesPage clickAddCurrenciesButton() throws InterruptedException {
        AbstractSeleniumPage.clickButton(addCurrenciesButton);
        return this;
    }

    public PayGradesPage clickSavePGButton() throws InterruptedException {
        AbstractSeleniumPage.clickButton(savePGButton);
        return this;
    }

    public PayGradesPage clickSaveCurrencyButton() throws InterruptedException {
        AbstractSeleniumPage.clickButton(saveCurrencyButton);
        return this;
    }

    public PayGradesPage enterName(String name) throws InterruptedException, AWTException {
        AbstractSeleniumPage.sendKeysInElement(nameField, name);
        return this;
    }

    public PayGradesPage shouldBeVisibleSuccessMessagePG() throws InterruptedException {
        int tries = 0;
        do {
            try {
                if (tries == 4) {
                    Assert.assertTrue(false, "error");
                }
                tries++;
                clickSavePGButton();
                System.out.println("Attempt " + tries);
                LOG.info("visible success message - " + successMessage.isDisplayed());
                break;
            } catch (StaleElementReferenceException | NotFoundException e) {
                AbstractSeleniumPage.freeze(2000);
            }
        } while (tries < 4);
        return this;
    }

    public PayGradesPage shouldBeVisibleSuccessMessageCurrency() throws InterruptedException {
        int tries = 0;
        do {
            try {
                if (tries == 4) {
                    Assert.assertTrue(false, "error");
                }
                tries++;
                clickSavePGButton();
                System.out.println("Attempt " + tries);
                LOG.info("visible success message - " + successMessage.isDisplayed());
                break;
            } catch (StaleElementReferenceException | NotFoundException e) {
                AbstractSeleniumPage.freeze(2000);
            }
        } while (tries < 4);
        return this;
    }

    public PayGradesPage enterMinSalary(String value) throws InterruptedException {
        minimumSalary.sendKeys(value);
        LOG.info("Put min value = " + value);
        return this;
    }

    public PayGradesPage enterMaxSalary(String value) {
        maximumSalary.sendKeys(value);
        LOG.info("Put max value = " + value);
        return this;
    }

    public void clickSelectButtonAndChooseMoney() throws InterruptedException, AWTException {
        Robot robot = new Robot();
        selectButton.click();
        AbstractSeleniumPage.scrollIntoView("down");
        AbstractSeleniumPage.freeze(5000);
        AbstractSeleniumPage.refreshPage();
        AbstractSeleniumPage.freeze(5000);
        addCurrenciesButton.click();
        selectButton.click();
        robot.keyPress(KeyEvent.VK_I);
        AbstractSeleniumPage.freeze(200);
        robot.keyPress(KeyEvent.VK_ENTER);
    }
}
