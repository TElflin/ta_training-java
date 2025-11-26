package com.epam.training.mateusz_smola.pageobject_model.test;

import com.epam.training.mateusz_smola.pageobject_model.driver.DriverSingleton;
import com.epam.training.mateusz_smola.pageobject_model.page.SauceLoginPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SauceLoginPageTest {

    private WebDriver driver;
    public static SauceLoginPage loginPage;

    @BeforeEach
    public void setup() {
        log.info("Setting up for tests");
        driver = DriverSingleton.getDriver();
        loginPage = new SauceLoginPage(driver);
    }

    @AfterEach
    public void tearDown() {
        log.info("Clearing after tests");
        DriverSingleton.closeDriver();
    }


    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/SourceAnyLoginCredentials.csv", delimiterString = ",")
    void testLoginWithErasedUsername(String username, String password) {

        log.info("Start logging test, no username");
        logUsedCredentials(username, password);
        String expectedMessage = "Username is required";
        String actualMessage = loginPage.openPage()
                .enterCredentials(username, password)
                .clearUsername()
                .clickLoginWrongCredentials()
                .getErrorMessage();

        assertThat(actualMessage).as("Check if error username message, as expected").contains(expectedMessage);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/SourceAnyPasswordCredentials.csv", delimiterString = ",")
    void testLoginWithErasedPassword(String username, String password) {

        log.info("Start logging test, no password");
        logUsedCredentials(username, password);
        String expectedMessage = "Password is required";
        String actualMessage = loginPage.openPage()
                .enterCredentials(username, password)
                .clearPassword()
                .clickLoginWrongCredentials()
                .getErrorMessage();

        assertThat(actualMessage).as("Check if error password message, as expected").contains(expectedMessage);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/SourceCorrectLoginCredentials.csv", delimiterString = ",")
    public void testLoginWithValidCredentials(String username, String password) {

        log.info("Start logging test, correct credentials");
        logUsedCredentials(username, password);
        String expectedTitle = "Swag Labs";
        String actualTitle = loginPage.openPage()
                .enterCredentials(username, password)
                .clickLoginRightCredentials()
                .extractTitleName();

        assertThat(actualTitle).as("New site title check, after login").isEqualTo(expectedTitle);
    }

    private static void logUsedCredentials(String username, String password) {
        log.info("Start logging test Username: {} Password :{}", username, password);
    }

}



