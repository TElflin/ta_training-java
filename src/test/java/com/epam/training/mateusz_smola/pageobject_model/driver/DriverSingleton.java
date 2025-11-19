package com.epam.training.mateusz_smola.pageobject_model.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverSingleton {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(DriverSingleton.class);

    private DriverSingleton(){}

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            logger.info("Initializing WebDriver");
            String browser = System.getProperty("browser", "firefox");
            switch (System.getProperty("browser")) {
                case "edge" -> {
                    logger.info("Using Edge Driver");
                    driver.set( new EdgeDriver());
                }
                default -> {
                    logger.info("Using Firefox Driver");
                    driver.set( new FirefoxDriver());
                }
            }
        }
        driver.get().manage().window().maximize();
        return driver.get();
    }

    public static void closeDriver() {
        driver.get().quit();
        driver.remove();
        logger.info("Closing WebDriver");
    }
}
