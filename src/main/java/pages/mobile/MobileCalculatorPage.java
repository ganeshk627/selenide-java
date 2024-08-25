package pages.mobile;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
//import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import org.openqa.selenium.support.FindBy;

class MobileCalculatorPage {
    @FindBy(id = "op_add")
    @AndroidFindBy(id = "op-add")
//    @iOSFindBy(id = "operandAdd")
    @iOSXCUITFindBy(id = "operandAdd")
    private SelenideElement plus;

}