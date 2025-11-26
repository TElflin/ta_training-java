package com.epam.training.mateusz_smola.pageobject_model.page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.Keys;


@Slf4j
public class SauceLoginPage extends AbstractPage {
    private static final String LOGIN_PAGE_URL = "https://www.saucedemo.com/";

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
        log.info("Opening page");
        openPage(LOGIN_PAGE_URL);
        waitForButton(loginButton);
        return this;
    }

    public SauceLoginPage enterCredentials(String userLogin, String userPassword) {
        log.info("Entering login");
        username.sendKeys(userLogin);
        password.sendKeys(userPassword);
        return this;
    }

    public SauceLoginPage clearUsername() {
        log.info("Clearing username field");
        username.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        return this;
    }

    public SauceLoginPage clearPassword() {
        log.info("Clearing password field");
        password.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        return this;
    }


    public SauceAwesomeWaresPage clickLoginRightCredentials() {
        log.info("Logging");
        waitForButton(loginButton);
        loginButton.click();
        return new SauceAwesomeWaresPage(driver);
    }

    public SauceLoginPage clickLoginWrongCredentials() {
        log.info("Logging (Wrong Credentials)");
        waitForButton(loginButton);
        loginButton.click();
        return this;
    }

    public String getErrorMessage() {
        log.info("Searching for error message");
        waitForMessage(errorMessage);
        return errorMessage.getText();
    }

}
