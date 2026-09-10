package com.epam.framework.utils;

import com.epam.framework.config.ConfigReader;
import com.epam.framework.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.function.Function;

public final class WaitUtils {

    private WaitUtils() {
    }

    private static WebDriverWait getWait() {
        int timeoutSeconds = parseWaitOrDefault(ConfigReader.getProperty("explicitWait"));
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds));
    }

    private static int parseWaitOrDefault(String value) {
        if (value == null) {
            return 10;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 10;
        }
    }

    public static void waitForPageLoad() {
        WebDriver driver = DriverManager.getDriver();
        getWait().until((Function<WebDriver, Boolean>) d ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
    }

    public static WebElement waitForVisibility(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }
}