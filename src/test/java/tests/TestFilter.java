package tests;

import factories.WebDriverFactory;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LeftMenuPage;
import pages.LoginPage;
import pages.MainPage;
import service.AbstractSeleniumPage;
import service.AbstractSeleniumTest;

import java.util.List;
import java.util.Locale;

import static utils.Utils.freeze;

public class TestFilter extends AbstractSeleniumTest {

    private static SoftAssert softAssert = new SoftAssert();

    @Test
    public void test123(){

    }

    @Description(value = "Verify the results contains user(s) with " +
            "name containing the Name field input “ch”")
    @Test
    public void testFilterForNamesAndCountry() throws Exception {
        LoginPage loginPage = new LoginPage();
        loginPage.autorization("Orange Orange");
        LeftMenuPage leftMenuPage = new LeftMenuPage();
        leftMenuPage.clickButtonFromMenuForName("Directory");
        freeze(2000);
        MainPage mainPage = new MainPage();
        AbstractSeleniumPage.refreshPage();
        mainPage.fillFieldWithValue("ch")
                .clickLocationField()
                .chooseOneOfLocation()
                .clickSearchButton();
        checkNamesResult();
        checkCountryResult();
        mainPage.logout();
        LoginPage loginPage1 = new LoginPage();
        softAssert.assertAll();
    }

    public void checkNamesResult() {
        List<WebElement> namesList = WebDriverFactory.getCurrentWebDriver().findElements(By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-directory-card-header --break-words']"));
        for (WebElement element : namesList) {
            softAssert.assertTrue(element.getText().toLowerCase(Locale.ROOT).contains("ch"), "Person with name " + element.getText() + " doesn't contain name with 'Ch'");
        }
    }

    public void checkCountryResult() {
        final String expectedResult = "HQ - CA, USA";
        List<WebElement> countryList = WebDriverFactory.getCurrentWebDriver().findElements(By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-directory-card-description --break-words' and text()='HQ - CA, USA']"));
        for (WebElement element : countryList) {
            softAssert.assertEquals(expectedResult, element.getText(), element.getText() + " doesn't equals expected result");
        }
    }
}


