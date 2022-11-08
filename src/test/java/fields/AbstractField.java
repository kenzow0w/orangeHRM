package fields;

import com.codeborne.selenide.SelenideElement;

abstract public class AbstractField {

    protected SelenideElement selenideElement;
    private String className;
    protected String xpathPath;

    public AbstractField(SelenideElement selenideElement) {
        this.selenideElement = selenideElement;
    }

    public void click(){
        selenideElement.click();
    }

    public void doubleClick(){
        selenideElement.doubleClick();
    }

    public void isDisplayed(){
        selenideElement.isDisplayed();
    }



}
