package web.practices;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.ClipboardConditions.content;
import static com.codeborne.selenide.Selenide.*;


public class ClipboardCheck {

    @Test
    void clipboardText() {
//        WebDriverManager.chromedriver().clearDriverCache();
        WebDriverManager.chromedriver().setup();

        open("https://selenide.org/test-page/clipboard.html");

        $("#text-input").val("Hello LambdaTest!");
        $("#copy-button").click();
        clipboard().shouldHave(content("Hello LambdaTest!"));


    }
}
