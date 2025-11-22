package com.epam.training.mateusz_smola.pageobject_model.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverSingleton {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//TODO I recommend to use lombok for logger initialization, it's a standard in many projects
    private static final Logger logger = LoggerFactory.getLogger(DriverSingleton.class);
//TODO Lombok can generate private constructors for you as well using @UtilityClass or @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private DriverSingleton(){}

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            logger.info("Initializing WebDriver");
            String browser = System.getProperty("browser", "firefox");
//            Todo if some one run your code against unknown browser it will default to firefox
//            better to throw exception in such case to avoid confusion
            switch (browser) {
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
//       TODO Can be NullPointerException.
//To avoid this, you should check if driver.get() is not null before calling quit().
// This ensures safe cleanup and prevents runtime errors.
        driver.get().quit();
        driver.remove();
        logger.info("Closing WebDriver");
    }
}
