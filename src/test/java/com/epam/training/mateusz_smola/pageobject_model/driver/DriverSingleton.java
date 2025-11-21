package com.epam.training.mateusz_smola.pageobject_model.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverSingleton {
    private static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(DriverSingleton.class);

    private DriverSingleton(){}

    public static WebDriver getDriver() {
        if (driver == null) {
            logger.info("Initializing WebDriver");
            String browser = System.getProperty("browser","firefox");
            switch (browser) {
                case "edge" -> {
                    logger.info("Using Edge Driver");
                    driver =  new EdgeDriver();
                }
                default -> {
                    logger.info("Using Firefox Driver");
                    driver = new FirefoxDriver();
                }
            }
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static void closeDriver() {
        logger.info("Closing WebDriver");
        driver.quit();
        driver = null;

    }
}
