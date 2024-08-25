package mobile.android.practices;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class Addition {
    @Test
    void mobileCalculator() {
        $(By.name("2")).click();
        $(By.name("+")).click();
        $(By.name("4")).click();
        $(By.name("=")).click();
        $(By.className("android.widget.EditText")).shouldHave(text("6"));
    }
}
