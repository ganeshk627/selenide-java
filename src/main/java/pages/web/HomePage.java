package pages;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private SelenideElement logoutButton = $("#logoutButton");

    public boolean isLoggedIn() {
        return logoutButton.isDisplayed();
    }
}