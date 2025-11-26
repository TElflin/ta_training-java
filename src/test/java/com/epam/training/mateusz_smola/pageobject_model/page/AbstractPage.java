package com.epam.training.mateusz_smola.pageobject_model.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractPage {

    protected WebDriver driver;

    protected AbstractPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String extractTitleName() {
        return driver.getTitle();
    }

    protected void openPage(String url) {
        driver.get(url);
    }

    // WAITS
    protected void waitForButton(WebElement button) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(button));
    }

    protected void waitForMessage(WebElement message) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(driver1 -> message.isDisplayed());
    }

}
