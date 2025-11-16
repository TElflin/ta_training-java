package com.epam.training.mateusz_smola.pageobject_model.test;

import com.epam.training.mateusz_smola.pageobject_model.page.SauceLoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/// **
// * Unit test for simple App.
// */
public class SauceLoginPageTest {

    public static WebDriver driver;
    private static SauceLoginPage loginPage;

    @BeforeAll
    public static void browserSetup() {
        driver = new FirefoxDriver();
        loginPage = new SauceLoginPage(driver);
    }

    @Test
    public void logging_validCreditentila_siteOpen() {

        Assertions.assertTrue(true);

    }

    @AfterAll
    public static void closeBrowser() {
        driver.quit();
    }

}



