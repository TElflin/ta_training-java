package com.epam.training.mateusz_smola.pageobject_model.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceAwesomeWaresPage extends AbstractPage {

    @FindBy(xpath = "//head/title")
    private WebElement title;

    public SauceAwesomeWaresPage(WebDriver driver) {
        super(driver);
    }

    public String extractTitleName() {
        return driver.getTitle();
    }
}
