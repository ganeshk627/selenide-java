package web.smoke;

import org.junit.jupiter.api.Test;
import pages.web.GoogleAnnotatedPage1;
import pages.web.GoogleAnnotatedPage2;
import pages.web.GooglePage;

import static com.codeborne.selenide.Selenide.page;

public class GoogleTest {
    @Test
    void googleSearch() {
        GooglePage googlePage = new GooglePage();
        googlePage.openGoogle();
        googlePage.search("Hello");
    }

    @Test
    void googleSearchWithAnnoation1() {
        GoogleAnnotatedPage1 googlePage = page();
        googlePage.openGoogle();
        googlePage.search("Hello");
    }

    @Test
    void googleSearchWithAnnoation2() {
        GoogleAnnotatedPage2 googlePage = new GoogleAnnotatedPage2();
        googlePage.openGoogle();
        googlePage.search("Hello");
    }
}
