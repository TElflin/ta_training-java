package com.epam.training.mateusz_smola.pageobject_model.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SauceLoginPage {
    private static final String LOGIN_PAGE_URL = "https://www.saucedemo.com/";
    private WebDriver driver;


    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement username;


    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;


    public SauceLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
        username.clear();
        return this;
    }

    public SauceLoginPage clearPassword() {
        password.clear();
        return this;
    }

    public SauceAwsomeWaresPage clickLogin() {

        loginButton.click();
        return new SauceAwsomeWaresPage(driver);
    }

}
