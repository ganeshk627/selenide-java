package web.practices;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class AllureReport {

    @Test
    void search() {
//        WebDriverManager.chromedriver().clearDriverCache();
        WebDriverManager.chromedriver().setup();

        open("https://selenide.org");

        $(".main-menu-pages").find(byText("Users")).click();
        System.out.println("Clicked Users!");

        System.out.println($$("#user-tags .tag").size());
        $$("#user-tags .tag").shouldHave((sizeGreaterThan(8)));
//        $$("#user-tags .tag").shouldHave((sizeGreaterThan(8)), texts("One", "Two", "Three"));

        System.out.println($$("#selenide-users .user").size());
        $$("#selenide-users .user").shouldHave((CollectionCondition.sizeGreaterThan(20)));
    }


    // Allure reporting
    @BeforeAll
    static void setUpAllure() {
        SelenideLogger.addListener( "allure", new AllureSelenide());
    }
}


