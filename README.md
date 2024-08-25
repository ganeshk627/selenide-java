# selenide-java

# Introduction to Selenide
Selenide is a robust and user-friendly framework for test automation, primarily used for web application testing. It is built on top of Selenium WebDriver and enhances Selenium by providing a more concise API, automatic waiting, and easier handling of web elements, making it a preferred choice for many automation engineers.

# Key Features of Selenide
1. **Web Testing**: Selenide excels in automating web applications. It simplifies the process of interacting with web elements, managing browser sessions, and handling synchronization issues, making web UI testing more efficient and reliable.
2. **Mobile Web Testing**: While Selenide is primarily designed for web testing, it can also be used to test mobile web applications. By configuring browser emulation or running tests on real mobile devices via cloud services like BrowserStack or Sauce Labs, Selenide can help ensure that your web application works well across different mobile devices and browsers.
3. **API Testing**: Selenide itself is not designed for API testing. For API testing, tools like RestAssured, Postman, or similar are more suitable. However, Selenide can be integrated with API testing frameworks within the same test suite, allowing you to perform UI and API tests in tandem.
4. **Cross-Browser Testing**: Selenide supports all major browsers, including Chrome, Firefox, Edge, Opera, and Safari. It allows you to run tests across different browsers to ensure cross-browser compatibility of your web application.

# Language Support
Selenide is primarily a Java-based framework. It supports Java and other JVM languages like Kotlin and Scala. The focus on the Java ecosystem makes it a good fit for teams already using Java for development or testing, and it integrates seamlessly with popular build tools like Maven and Gradle.
- **Java**: The primary language for Selenide. Most of the documentation and examples are in Java.
- **Kotlin**: Selenide works well with Kotlin, a modern and concise JVM language.
- **Scala**: Selenide can also be used with Scala, although it is less common.

# Simple UI Test
```java
@Test
public void userCanLogin() {
    
    open("http://localhost:8080/login");
    $(By.name("user.name")).setValue("john");
    $("#submit").click();
    $(".menu").shouldHave(text("Hello, John!"));
    
}
```

# Concise Syntax for finding element
```java
$("h1");
```
Above ```selenide``` code is concise format of below ```selenium-java``` code
```java
driver.findElement(By.cssSelector("h1"));
```

# Concise Syntax for assertion
```java
$("h1") .shouldNot(exist);
```
Above ```selenide``` code is concise format of below ```selenium-java``` code
```java
try {
    WebElement element = driver.findElement(By.cssSelector("h1"));
    fail("Element should not exist: " + element);
} catch (WebdriverException itsOk) { }
```

# Selenide waiting - Smart waiting
```java
$("h1") .shouldBe(visible);
```
Above ```selenide``` code is concise format of below ```selenium-java``` code
```java
new WebDriverWait(driver, Duration.ofSeconds(4))
        .until(visibilityOfElementLocated(
        By.tagName("h1")));

```
> Note:- All the $.should*() methods wait up to 4 seconds

# Few more assertions with smart waiting
```java
$(".loading_progress").shouldBe(visible);

$("#menu").shouldHave(text("Hello, John!"));

$(By.name("gender")).shouldNotBe(selected);

$(By.name("gender")).should(disappear);
```

# Timeout configuration
```bash
mvn -Dselenide.timeout=8000
```

# Handling Error Messages
```java
$(".alert").shouldNotHave(text("Error"));
```
```bash
Element should not have text 'Error' {.alert}
Element: '<div class=info alert>Error</div>'
Timeout 4 s.
```

# Collection of elements
 - ```$$``` returns collection of elements
```java
// Element count should be 3
$$(".error").shouldHave(size(3));

// Element text should have mentioned text values
$$("#employees tbody tr"). shouldHave(
        texts(
        "John Belushi”,
        "Bruce Willis",
        "John Malkovich"
        )
);

// Only the visible items count should be 4
$$("#employees tbody tr")
        .filter(visible)
        .shouldHave(size(4));

// Elements count should be more than 8 and should contain the mentioned text
$$("#user-tags .tag")
    .shouldHave(
            (sizeGreaterThan(8)),
            texts("One", "Two", "Three")
    );
```

# Find Elements

```java
// ID
$("#loginBtn");
$(By.id("loginBtn"));

// CLASS
$(".active");
$(By.className("active"));

// NAME
$(By.name("username"));
$("[name=username]");

// XPATH
$x("//div//h1");
$(By.xpath("//div//h1"));
```

# Find elements by attributes
```java
import static com.codeborne.selenide.Selectors.*;

$(by("type", "checkbox"));
$(by("readonly", "readonly"));
$(byTitle("Login form"));
$(byValue("Alert button"));
$(by("data-test-id", "alert-button"));
```

# Find elements by text
```java
import static com.codeborne.selenide.Selectors.*;
$(byText("Hello, Testμ 2024!"))
    .shouldBe(visible);
$(withText("ello"))
    .shouldHave(text("Hello, Testμ 2024!"));
```

# Find parents and children
- $.parent()
- $.closest("table”)
```java
$("table td[data-foo='bar']")
    .closest("table")
    .find("tr", 4)
    .find(byText("THE CELL"))
    .click();
```

# Reports
## 1. Selenide "text report"
Report in table format with element, subject(action), time taken
```java
@ExtendWith(TextReportExtension.class)
class MyTest {
@Test void one() {}
@Test void two() {}
}
```
```properties
# src/test/resources/junit-platform.properties
junit.jupiter.extensions.autodetection.enabled=true
```
## 2. Selenide + Allure Report
```xml
<!--pom.xml ==> Properties -->
<properties>
    <allure.version>2.29.0</allure.version>
    <aspectj.version>1.9.22.1</aspectj.version>
</properties>

<!--pom.xml ==> Dependencies -->
 <dependencies>
    <dependency>
      <groupId>io.qameta.allure</groupId>
      <artifactId>allure-junit5</artifactId>
      <version>${allure.version}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>io.qameta.allure</groupId>
      <artifactId>allure-selenide</artifactId>
      <version>${allure.version}</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
        
 <!--pom.xml ==> Build Plugin -->
<build>

<plugins>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.4.0</version>
        <configuration>
            <testFailureIgnore>false</testFailureIgnore>
            <argLine>
                -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
            </argLine>
            <properties>
                <property>
                    <name>listener</name>
                    <value>io.qameta.allure.junit5.AllureJunit5</value>
                </property>
            </properties>
            <systemProperties>
                <property>
                    <name>allure.results.directory</name>
                    <value>${project.build.directory}/allure-results</value>
                </property>
            </systemProperties>
            <systemPropertyVariables>
                <selenide.headless>true</selenide.headless>
            </systemPropertyVariables>
        </configuration>
        <dependencies>
            <dependency>
                <groupId>org.aspectj</groupId>
                <artifactId>aspectjweaver</artifactId>
                <version>${aspectj.version}</version>
            </dependency>
        </dependencies>
    </plugin>

    <plugin>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-maven</artifactId>
        <version>2.13.0</version>
        <configuration>
            <reportVersion>${allure.version}</reportVersion>
        </configuration>
    </plugin>
</plugins>
</build>
```
```java
// BaseTest.java
abstract class BaseTest {
@BeforeAll
static void setUpAllure() {
    SelenideLogger.addListener( "allure", new AllureSelenide());
}
```
```bash
# running the tests
mvn clean test
```
```bash
# running report in any port
mvn allure:serve

# running report in particular port
mvn allure:serve -Dallure.serve.port={your_port}

# generate report
mvn allure:report

```
> Note:- [Refer more here](https://github.com/selenide-examples/selenide-allure-junit/)

# Uploading a single file
```java
$("#cv").uploadFile(
        new File("cv1.doc")
);
```

# Uploading more files
```java
$("#cv").uploadFile(
    new File("cv1.doc"),
    new File("cv2.doc"),
    new File("cv3.doc"), new File("cv4.doc")
);
```

# Downloading files
```java
File simpleDownload = $("#download").download();

File advacedDownload = $("#download").download(
        using(PROXY)
                .withTimout(ofSeconds(60))
                .withFilter(withExtension("txt"))
);
```
> Note1:- All the files will be downloaded under ```build/downloads/*/filename.extension```

> Note2:- If want to verify the file data, [refer here](https://github.com/codeborne/pdf-test)

# URL Checks
```java
// Validate URL
webdriver().shouldHave(
        url("https://auth.google.com")
);

// Validate URL with specific timeout
webdriver().shouldHave(
        url("https://auth.google.com"),
        Duration.ofSeconds(42)
);
```

# Clipboard Checks
```java
clipboard().shouldHave(
        content("Hello harry")
);
```

# Local Storage Checks
```java
localStorage().shouldHave(
        item("cat")
);

sessionStorage().shouldHave(
        itemWithValue("school", "Hogwarts")
);
```

# Page Object for Web
* no factory - no need to pass driver reference
* no annotations
* Just only code
```java

public class GooglePage {
    public void search(String keyToSearch) {
        $(By.name("q"))
                .val(keyToSearch)
                .pressEnter();
    }
}
```
* Still possible with @FindBy
```java

public class GooglePage {
    @FindBy(name = "q")
    private SelenideElement searchBox;
    public void search(String keyToSearch) {
        searchBox
                .val(keyToSearch)
                .pressEnter();
    }
}
```

# Selenide for mobile (Appium)
- [Selenide Integration with Appium](https://github.com/selenide/selenide/tree/main/modules/appium)
- [Selenide Appium Example](https://github.com/selenide-examples/selenide-appium)

# Simple Mobile Test
```java
@Test void mobileCalculator() {
    $(By.name("2")).click();
    $(By.name("+")).click();
    $(By.name("4")).click();
    $(By.name("=")).click();
    $(By.className("android.widget.EditText")).shouldHave(text("6"));
}
```

# Page Objects for Mobile
```java
class MobileCalculatorPage {
    @FindBy(id = "op_add")
    @AndroidFindBy(id = "op-add")
    @iOSFindBy(id = "operandAdd")
    private SelenideElement plus;
}
```

# Run Selenide Tests on LambdaTest
1. Declare two env variables
```bash
# LambdaTest credentials
export LT_USERNAME=gk****
export LT_ACCESS_KEY=6************************************7
```
2. Before tests

```java
Configuration.remote ="https://hub.lambdatest.com/wd/hub";

Configuration.browserCapabilities.setCapability("LT:Options",
    Map.of(
            "user", System.getenv("LT_USERNAME"),
            "accessKey", System.getenv("LT_ACCESS_KEY"),
            "seCdp", true,
            "selenium_version", "4.23.0"
    )
);
```




# Resources
https://github.com/selenide-examples/
https://www.youtube.com/watch?v=18J2_4a4Cl4
