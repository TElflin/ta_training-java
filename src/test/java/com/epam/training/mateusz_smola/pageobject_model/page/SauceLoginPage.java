package com.epam.training.mateusz_smola.pageobject_model.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import java.time.Duration;

public class SauceLoginPage extends AbstractPage{
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
        driver.get(LOGIN_PAGE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        return this;
    }

    public SauceLoginPage enterCredentials(String userLogin, String userPassword) {
        username.sendKeys(userLogin);
        password.sendKeys(userPassword);
        return this;
    }

    public SauceLoginPage clearUsername() {
        username.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        return this;
    }

    public SauceLoginPage clearPassword() {
        password.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));

        return this;
    }


    public SauceAwesomeWaresPage clickLoginRightCredentials() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginButton));

        loginButton.click();
        return new SauceAwesomeWaresPage(driver);
    }

    public SauceLoginPage clickLoginWrongCredentials() {

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElement(username,""));

        System.out.println("Wypidsz pole tekstowe " + username.getText());

        loginButton.click();
        return this;
    }

    public String getErrorMessage ()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver1 -> errorMessage.isDisplayed());
        return errorMessage.getText();
    }

}
