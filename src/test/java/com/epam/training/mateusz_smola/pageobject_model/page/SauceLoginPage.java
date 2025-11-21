package com.epam.training.mateusz_smola.pageobject_model.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class SauceLoginPage extends AbstractPage{
    private static final String LOGIN_PAGE_URL = "https://www.saucedemo.com/";
    private static final Logger logger = LoggerFactory.getLogger(SauceLoginPage.class);

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;




    public SauceLoginPage(WebDriver driver) {
        super(driver);
    }

    public SauceLoginPage openPage() {
        logger.info("Opening page");
        driver.get(LOGIN_PAGE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        return this;
    }

    public SauceLoginPage enterCredentials(String userLogin, String userPassword) {
        logger.info("Entering login");
        username.sendKeys(userLogin);
        password.sendKeys(userPassword);
        return this;
    }

    public SauceLoginPage clearUsername() {
        logger.info("Clearing username field");
        username.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(username,"ok"));
        return this;
    }

    public SauceLoginPage clearPassword() {
        logger.info("Clearing password field");
        password.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(password,"ok"));

        return this;
    }


    public SauceAwesomeWaresPage clickLoginRightCredentials() {
        logger.info("Logging");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginButton));

        loginButton.click();
        return new SauceAwesomeWaresPage(driver);
    }

    public SauceLoginPage clickLoginWrongCredentials() {
        logger.info("Logging (Wrong Credentials)");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginButton));

        loginButton.click();
        return this;
    }

    public String getErrorMessage ()
    {
        logger.info("Searching for error message");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver1 -> errorMessage.isDisplayed());

        return errorMessage.getText();
    }

}
