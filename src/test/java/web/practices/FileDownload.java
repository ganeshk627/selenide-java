package web.practices;

import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class FileDownload {
    @Test
    void downloadFile() {

//        File file = createTemporaryFile();
        open("https://the-internet.herokuapp.com/download");

        File simpleDownload = $x("//a[normalize-space()='img.png']").download();
        System.out.println(simpleDownload.getName());

    }
}
