package com.epam.training.mateusz_smola.pageobject_model.test;

import com.epam.training.mateusz_smola.pageobject_model.page.SauceLoginPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.assertj.core.api.Assertions.assertThat;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SauceLoginPageTest {

    private WebDriver driver;
    public static SauceLoginPage loginPage;

    @BeforeAll
    public void browserSetup() {
        driver = new FirefoxDriver();
//        driver = new EdgeDriver();
        loginPage = new SauceLoginPage(driver);
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/SouceLoginCredentials.csv", delimiterString = ",")
    void testLoginWithErasedUsername (String username, String password) {
        String expectedMessage = "Username is required";
        String actualMessage = loginPage.openPage().enterCredentials(username, password)
                .clearUsername().clickLoginWrongCredentials().getErrorMessage();

        assertThat(actualMessage).contains(expectedMessage);
    }
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/SouceLoginCredentials.csv", delimiterString = ",")
    void testLoginWithErasedPassword (String username, String password) {
        String expectedMessage = "Password is required";
        String actualMessage = loginPage.openPage().enterCredentials(username, password)
                .clearPassword().clickLoginWrongCredentials().getErrorMessage();

        assertThat(actualMessage).contains(expectedMessage);
    }
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/SouceLoginCredentials.csv", delimiterString = ",")
    public void testLoginWithValidCredentials (String username, String password) {
        String expectedTitle = "Swag Labs";
        String actualTitle = loginPage.openPage().enterCredentials(username, password)
                .clickLoginRightCredentials().extractTitleName();

        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

}



