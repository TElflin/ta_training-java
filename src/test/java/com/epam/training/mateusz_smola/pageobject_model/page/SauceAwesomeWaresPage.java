package com.epam.training.mateusz_smola.pageobject_model.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SauceAwesomeWaresPage extends AbstractPage {
//TODO your logger is never used, consider removing it but better use Lombok @Slf4j annotation to generate it for you
    private static final Logger logger = LoggerFactory.getLogger(SauceAwesomeWaresPage.class);

    public SauceAwesomeWaresPage(WebDriver driver) {
        super(driver);
    }

    public String extractTitleName() {

//        Todo
        return driver.getTitle();
    }
}
