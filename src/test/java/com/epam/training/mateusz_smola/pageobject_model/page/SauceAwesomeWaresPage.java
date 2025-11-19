package com.epam.training.mateusz_smola.pageobject_model.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SauceAwesomeWaresPage extends AbstractPage {

    private static final Logger logger = LoggerFactory.getLogger(SauceAwesomeWaresPage.class);

    public SauceAwesomeWaresPage(WebDriver driver) {
        super(driver);
    }

    public String extractTitleName() {
        logger.info("Extracting title");
        return driver.getTitle();
    }
}
