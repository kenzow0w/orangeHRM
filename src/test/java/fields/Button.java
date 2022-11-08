package fields;

import com.codeborne.selenide.SelenideElement;

public class Button extends AbstractField{

    public Button(SelenideElement selenideElement) {
        super(selenideElement);
    }

    @Override
    public void click() {
        selenideElement.click();
    }

    @Override
    public void doubleClick() {
        selenideElement.doubleClick();
    }

    @Override
    public void isDisplayed() {
        selenideElement.isDisplayed();
    }
}
