package tests;

import exceptions.AutotestException;
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

public class taskTest2 extends AbstractSeleniumTest {

    private static SoftAssert softAssert = new SoftAssert();
    private final String URL = "https://opensource-demo.orangehrmlive.com/";
    private int randomNumber = (int) (Math.random() * (30 + 1)) + 10;
    private final String name = "Grade " + randomNumber;
    private final String currency = "Indonesian Rupiah";
    private final String minSalary = "30000";
    private final String maxSalary = "100000";

    @Test
    public void testCase2() throws Exception {
        AbstractSeleniumPage.openWebSite(URL);
        LoginPage loginPage = new LoginPage();
        loginPage.autorization(username, pass);
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
        payGradesPage.enterMinSalary(minSalary)
                .enterMaxSalary(maxSalary)
                .clickSaveCurrencyButton()
                .shouldBeVisibleSuccessMessageCurrency();

        AbstractSeleniumPage.freeze(5000);
        checkInformationAboutPosition();
    }

    private void checkInformationAboutPosition() throws AutotestException {
        LOG.info("Check results !!!");
        String regex = "(.{3}$)";
        String xpath1 = "//div[@class='oxd-table-row oxd-table-row--with-border']/descendant::div[@class='header'] | //div[@class='oxd-table-header-cell oxd-padding-cell oxd-table-th'][position()>1 and position()<5]";
        String xpath2 = "//div[@class='oxd-table-row oxd-table-row--with-border']/descendant::div[@class='data'] | //div[@class='oxd-table-cell oxd-padding-cell'][position()>1 and position()<5]";
        WebDriverWait wait = new WebDriverWait(AbstractSeleniumPage.getWebDriver(), Duration.ofSeconds(20));
        List<WebElement> listHeader = AbstractSeleniumPage.getWebDriver().findElements(By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border']/descendant::div[@class='header'] | //div[@class='oxd-table-header-cell oxd-padding-cell oxd-table-th'][position()>1 and position()<5]"));
        List<WebElement> listValues = AbstractSeleniumPage.getWebDriver().findElements(By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border']/descendant::div[@class='data'] | //div[@class='oxd-table-cell oxd-padding-cell'][position()>1 and position()<5]"));
        wait.until(ExpectedConditions.visibilityOf(listValues.get(0)));
        Iterator<WebElement> iterator = listValues.iterator();
        while (iterator.hasNext()) {
            for (WebElement element : listHeader) {
                if (!Evaluator.containsKey(element.getText())) {
                    String value =iterator.next().getText();
                    if(value.contains(".")){
                        Evaluator.setVariable(element.getText(), value.replaceAll(regex, ""));
                    }else {
                        Evaluator.setVariable(element.getText(), value);
                    }

                    break;
                }
            }
        }
        softAssert.assertEquals(Evaluator.getVariable("Currency"), currency);
        softAssert.assertEquals(Evaluator.getVariable("Minimum Salary"), minSalary);
        softAssert.assertEquals(Evaluator.getVariable("Maximum Salary"), maxSalary);
        softAssert.assertAll();
    }
}