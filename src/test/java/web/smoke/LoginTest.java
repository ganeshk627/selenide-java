package web.smoke;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.web.HomePage;
import pages.web.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    @BeforeAll
    public static void setup() {
        // Configure browser settings
        Configuration.browser = "chrome";
//        Configuration.startMaximized = true; // not working probally new methodology available in newer versions
        Configuration.timeout = 10000; // Set timeout to 10 seconds
    }

    @Test
    public void successfulLoginTest() {
        // Open the login page
        LoginPage loginPage = open("https://example.com/login", LoginPage.class);
        
        // Perform login
        loginPage.login("validUser", "validPassword");
        
        // Verify successful login
        HomePage homePage = new HomePage();
        assertTrue(homePage.isLoggedIn());
    }

    @Test
    public void failedLoginTest() {
        // Open the login page
        LoginPage loginPage = open("https://example.com/login", LoginPage.class);
        
        // Perform login with invalid credentials
        loginPage.login("invalidUser", "invalidPassword");
        
        // Verify the error message
        assertEquals("Invalid username or password", loginPage.getErrorMessage());
    }
}