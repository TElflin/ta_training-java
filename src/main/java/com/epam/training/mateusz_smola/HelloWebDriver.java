package com.epam.training.mateusz_smola;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Hello world!
 */
public class HelloWebDriver {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        new WebDriverWait (driver, Duration.ofSeconds(3))
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//input[@id='login-button']")));
        WebElement userName = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@id='login-button']"));
        userName.sendKeys("standard_user\n");
        password.sendKeys("secret_sauce\n");
        loginButton.click();

        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            System.out.println("Cos tam cos tam");
        }
        finally {
            driver.quit();

        }
    }
}
