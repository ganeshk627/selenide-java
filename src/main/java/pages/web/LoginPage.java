package pages.web;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class LoginPage {
    
    // Locators
    private SelenideElement usernameField = $("#username");
    private SelenideElement passwordField = $("#password");
    private SelenideElement loginButton = $("#loginButton");
    private SelenideElement errorMessage = $("#error");

    // Actions
    public void enterUsername(String username) {
        usernameField.setValue(username);
    }

    public void enterPassword(String password) {
        passwordField.setValue(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.shouldBe(visible).getText();
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}