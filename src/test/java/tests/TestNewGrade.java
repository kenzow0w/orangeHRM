package tests;

import exceptions.AutotestException;
import factories.WebDriverFactory;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LeftMenuPage;
import pages.LoginPage;
import pages.MainPage;
import pages.PayGradesPage;
import service.AbstractSeleniumPage;
import service.AbstractSeleniumTest;
import utils.Evaluator;

import java.time.Duration;
import java.util.*;

public class TestNewGrade extends AbstractSeleniumTest {

    private static SoftAssert softAssert = new SoftAssert();
    private int randomNumber = (int) (Math.random() * (30 + 1)) + 10;
    private final String name = "Grade " + randomNumber;
    private final String expectedCurrency = "Indonesian Rupiah";
    private final String expectedMinSalary = "30000";
    private final String expectedMaxSalary = "100000";

    @Description(value = "Verify information " +
            "about new Grade, Salary range, currency")
    @Test
    public void testInformationAboutNewGrade() throws Exception {
        LoginPage loginPage = new LoginPage();
        loginPage.autorization("Orange Orange");
        LeftMenuPage leftMenuPage = new LeftMenuPage();
        leftMenuPage.clickAdminButton();
        AbstractSeleniumPage.refreshPage();
        MainPage mainPage = new MainPage();
        mainPage.clickJobButton()
                .clickElementForNameFromJobList("Pay Grades");
        AbstractSeleniumPage.refreshPage();
        PayGradesPage payGradesPage = new PayGradesPage();
        payGradesPage.clickAddPGButton();
        payGradesPage.enterName(name)
                .clickSavePGButton()
                .shouldBeVisibleSuccessMessagePG()
                .clickAddCurrenciesButton()
                .clickSelectButtonAndChooseMoney();
        payGradesPage.enterMinSalary(expectedMinSalary)
                .enterMaxSalary(expectedMaxSalary)
                .clickSaveCurrencyButton()
                .shouldBeVisibleSuccessMessageCurrency();

        AbstractSeleniumPage.freeze(5000);
        checkInformationAboutPosition();
        softAssert.assertAll();
    }

    private void checkInformationAboutPosition() throws AutotestException {
        LOG.info("Check results !!!");
        String regex = "(.{3}$)";
        String xpath1 = "//div[@class='oxd-table-row oxd-table-row--with-border']/descendant::div[@class='header'] | //div[@class='oxd-table-header-cell oxd-padding-cell oxd-table-th'][position()>1 and position()<5]";
        String xpath2 = "//div[@class='oxd-table-row oxd-table-row--with-border']/descendant::div[@class='data'] | //div[@class='oxd-table-cell oxd-padding-cell'][position()>1 and position()<5]";
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getCurrentWebDriver(), Duration.ofSeconds(20));
        List<WebElement> listHeader = WebDriverFactory.getCurrentWebDriver().findElements(By.xpath(xpath1));
        List<WebElement> listValues = WebDriverFactory.getCurrentWebDriver().findElements(By.xpath(xpath2));
        wait.until(ExpectedConditions.visibilityOf(listValues.get(0)));
        Iterator<WebElement> iterator = listValues.iterator();
        while (iterator.hasNext()) {
            for (WebElement element : listHeader) {
                if (!Evaluator.containsKey(element.getText())) {
                    String value = iterator.next().getText();
                    if (value.contains(".")) {
                        Evaluator.setVariable(element.getText(), value.replaceAll(regex, ""));
                    } else {
                        Evaluator.setVariable(element.getText(), value);
                    }
                    break;
                }
            }
        }
        softAssert.assertEquals(Evaluator.getVariable("Currency"), expectedCurrency);
        softAssert.assertEquals(Evaluator.getVariable("Minimum Salary"), expectedMinSalary);
        softAssert.assertEquals(Evaluator.getVariable("Maximum Salary"), expectedMaxSalary);
    }
}
