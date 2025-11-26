package com.epam.training.mateusz_smola.pageobject_model.driver;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverSingleton {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            log.info("Initializing WebDriver");
            String browser = System.getProperty("browser");
            switch (browser) {
                case "edge" -> {
                    log.info("Using Edge Driver");
                    driver.set(new EdgeDriver());
                }
                case "firefox" -> {
                    log.info("Using Firefox Driver");
                    driver.set(new FirefoxDriver());
                }
                case null, default -> {
                    log.info("No compatible browser specified : -Dbrowser=yourbrowser");
                    throw new IllegalArgumentException("No compatible browser specified : -Dbrowser=yourbrowser");
                }
            }
        }
        driver.get().manage().window().maximize();
        return driver.get();
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
        log.info("Closing WebDriver");
    }

}
