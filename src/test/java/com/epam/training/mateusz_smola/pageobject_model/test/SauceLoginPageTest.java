package com.epam.training.mateusz_smola.pageobject_model.test;

import com.epam.training.mateusz_smola.pageobject_model.page.SauceLoginPage;
import org.junit.jupiter.api.*;
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

    @Test
    void testLoginWithErasedUsername () {
        String expectedMessage = "Username is required";
        String actualMessage = loginPage.openPage().enterCredentials("standard_user", "secret_sauce")
                .clearUsername().clickLoginWrongCredentials().getErrorMessage();

        assertThat(actualMessage).contains(expectedMessage);
    }
    
    @Test
    void testLoginWithErasedPassword () {
        String expectedMessage = "Password is required";
        String actualMessage = loginPage.openPage().enterCredentials("standard_user", "secret_sauce").clearPassword()
                .clearPassword().clickLoginWrongCredentials().getErrorMessage();

        assertThat(actualMessage).contains(expectedMessage);
    }

    @Test
    public void testLoginWithValidCredentials() {
        String expectedTitle = "Swag Labs";
        String actualTitle = loginPage.openPage().enterCredentials("standard_user", "secret_sauce")
                .clickLoginRightCredentials().extractTitleName();

        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

}



