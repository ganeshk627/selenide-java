package pages.web;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GooglePage {
    public void openGoogle() {

/*
        In selenium, we will get NoSuchElementExection for the steps if no URL opened.
        But here we will get excetion as below (really cool)

        java.lang.IllegalStateException: No webdriver is bound to current thread: 1. You need to call open(url) first.
*/
        open("https://www.google.co.in/");

    }

    // Way 1
    public void search(String keyToSearch) {
        $(By.name("q"))
                .val(keyToSearch)
                .pressEnter();
    }


}
