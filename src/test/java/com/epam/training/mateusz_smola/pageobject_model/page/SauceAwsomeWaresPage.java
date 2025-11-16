package com.epam.training.mateusz_smola.pageobject_model.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SauceAwsomeWaresPage {
    private static final String LOGIN_PAGE_URL = "https://www.saucedemo.com/inventory.html";
    private WebDriver driver;

    @FindBy(xpath = "//title[text()='Swag Labs']")
    private WebElement title;

    public SauceAwsomeWaresPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String extractTitleName() {

        return title.getText();
    }
}
