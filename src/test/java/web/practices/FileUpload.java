package web.practices;

import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FileUpload {
    @Test
    void uploadFile() {

//        File file = createTemporaryFile();
        File file = new File("file1.txt");
        open("https://the-internet.herokuapp.com/upload");
        $("#file-upload").uploadFile(file);
        $("#file-submit").click();
        $("#uploaded-files").shouldHave(text(file.getName()));
        try {
            Thread.sleep(10000);
        } catch (Exception e) {}
    }
}
