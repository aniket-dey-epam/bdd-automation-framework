package com.epam.framework.utils;

import com.epam.framework.driver.DriverManager;
import com.epam.framework.exceptions.PageActionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public final class WebUtils {

    private WebUtils() {
    }

    private static WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public static void navigateTo(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("URL cannot be null or blank");
        }
        try {
            getDriver().get(url);
        } catch (WebDriverException e) {
            throw new PageActionException(
                    "Failed to navigate to URL: " + url, e
            );
        }
    }

    public static void maximizeWindow() {
        try {
            getDriver().manage().window().maximize();
        } catch (WebDriverException e) {
            throw new PageActionException(
                    "Failed to maximize browser window", e
            );
        }
    }

    public static void refreshPage() {
        try {
            getDriver().navigate().refresh();
        } catch (WebDriverException e) {
            throw new PageActionException(
                    "Failed to refresh the page", e
            );
        }
    }

    public static void goBack() {
        try {
            getDriver().navigate().back();
        } catch (WebDriverException e) {
            throw new PageActionException(
                    "Failed to navigate back", e
            );
        }
    }

    public static String getCurrentUrl() {
        try {
            return getDriver().getCurrentUrl();
        } catch (WebDriverException e) {
            throw new PageActionException(
                    "Failed to get current URL", e
            );
        }
    }

    public static String getPageTitle() {
        try {
            return getDriver().getTitle();
        } catch (WebDriverException e) {
            throw new PageActionException(
                    "Failed to get page title", e
            );
        }
    }
}