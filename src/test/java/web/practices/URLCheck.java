package web.practices;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;


import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverConditions.urlStartingWith;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;


public class URLCheck {

    @Test
    void search() {
//        WebDriverManager.chromedriver().clearDriverCache();
        WebDriverManager.chromedriver().setup();

        open("https://selenide.org");

        webdriver().shouldHave(url("https://selenide.org/"), Duration.ofSeconds(15));
        webdriver().shouldHave(urlStartingWith("https://selenide"));
        webdriver().shouldHave(urlContaining("selenide"));
    }
}
