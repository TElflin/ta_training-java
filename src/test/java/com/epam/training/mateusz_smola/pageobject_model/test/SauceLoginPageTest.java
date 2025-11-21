package com.epam.training.mateusz_smola.pageobject_model.test;

import com.epam.training.mateusz_smola.pageobject_model.driver.DriverSingleton;
import com.epam.training.mateusz_smola.pageobject_model.page.SauceLoginPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.assertj.core.api.Assertions.assertThat;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SauceLoginPageTest {

    private static final Logger logger = LoggerFactory.getLogger(SauceLoginPageTest.class);
    private WebDriver driver;
    public static SauceLoginPage loginPage;

    @BeforeAll
    public void setup() {
        logger.info("Setting up for tests");
        driver = DriverSingleton.getDriver();
        loginPage = new SauceLoginPage(driver);
    }

    @AfterAll
    public void tearDown() {
        logger.info("Clearing after tests");
        DriverSingleton.closeDriver();
    }



    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/SourceLoginCredentialsForFail.csv", delimiterString = ",")
    void testLoginWithErasedUsername (String username, String password) {
        logger.info("Start logging test, no username");
        String expectedMessage = "Username is required";
        String actualMessage = loginPage.openPage().enterCredentials(username, password).
                clearUsername().
                clickLoginWrongCredentials().
                getErrorMessage();

        assertThat(actualMessage).contains(expectedMessage);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/SourceLoginCredentialsForFail.csv", delimiterString = ",")
    void testLoginWithErasedPassword (String username, String password) {
        logger.info("Start logging test, no password");
        String expectedMessage = "Password is required";
        String actualMessage = loginPage.openPage().enterCredentials(username, password)
                .clearPassword()
                .clickLoginWrongCredentials()
                .getErrorMessage();

        assertThat(actualMessage).contains(expectedMessage);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/SourceLoginCredentials.csv", delimiterString = ",")
    public void testLoginWithValidCredentials (String username, String password) {
        logger.info("Start logging test" + "Username: " + username + " Password :" + password);
        String expectedTitle = "Swag Labs";
        String actualTitle = loginPage.openPage()
                .enterCredentials(username, password)
                .clickLoginRightCredentials()
                .extractTitleName();

        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

}



