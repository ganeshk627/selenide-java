package web.practices;

import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MultipleFileUpload {
    @Test
    void uploadMultipleFile() {

//        File file = createTemporaryFile();
        File file = new File("selenide-text-reporter.png");
        open("https://the-internet.herokuapp.com/upload");  // try different url
        $("#file-upload").uploadFile(
                new File("file1.txt"),
                new File("file2.txt"),
                new File("file3.txt")
        );
        $("#file-submit").click();
        $("#uploaded-files").shouldHave(text(file.getName()));
        try {
            Thread.sleep(10000);
        } catch (Exception e) {}
    }
}
