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
//    TODO let's use Lombok for logger initialization
    private static final Logger logger = LoggerFactory.getLogger(SauceLoginPage.class);

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

//Todo usually we use only one new line between methods


    public SauceLoginPage(WebDriver driver) {
        super(driver);
    }

    public SauceLoginPage openPage() {
        logger.info("Opening page");
        //TODO
//        If you've inherited from AbstractPage,
//        why not to override driver functionality there and have here just a method like:
//        void open(String url) this kind of methods are very common in Page Object Model pattern
//        so move them to AbstractPage can significantly reduce code duplication
//        The same is for waits, you can have common wait methods in AbstractPage as well
//        Long story short interaction with WebDriver should be mostly encapsulated in AbstractPage
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
        return this;
    }

    public SauceLoginPage clearPassword() {
        logger.info("Clearing password field");
        password.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));

        return this;
    }


    public SauceAwesomeWaresPage clickLoginRightCredentials() {
        logger.info("Logging");
//TODO move wait to AbstractPage as a common method
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginButton));

        loginButton.click();
        return new SauceAwesomeWaresPage(driver);
    }

    public SauceLoginPage clickLoginWrongCredentials() {
        logger.info("Logging (Wrong Credentials)");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElement(username,""));

        loginButton.click();
        return this;
    }

    public String getErrorMessage ()
    {
        logger.info("Searching for error message");
//TODO move wait to AbstractPage as a common method
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver1 -> errorMessage.isDisplayed());

        return errorMessage.getText();
    }

}
