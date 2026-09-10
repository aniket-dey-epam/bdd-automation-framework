package com.epam.framework.base;

import com.epam.framework.driver.DriverManager;
import com.epam.framework.exceptions.PageActionException;
import com.epam.framework.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public abstract class BasePage {

    protected WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = DriverManager.getDriver();
    }

    protected void click(By locator) {
        try {
            WaitUtils.waitForPageLoad();

            WebElement element = WaitUtils.waitForClickable(locator);
            element.click();

            WaitUtils.waitForPageLoad();

        } catch (TimeoutException e) {
            throw new PageActionException(
                    "Failed to click element: " + locator, e
            );
        }
    }

    protected void type(By locator, String text) {
        try {
            WaitUtils.waitForPageLoad();

            WebElement element = WaitUtils.waitForVisibility(locator);

            element.clear();
            element.sendKeys(text);

            WaitUtils.waitForPageLoad();

        } catch (TimeoutException e) {
            throw new PageActionException(
                    "Failed to type into element: " + locator, e
            );
        }
    }

    protected void hover(By locator) {
        try {
            WaitUtils.waitForPageLoad();

            WebElement element = WaitUtils.waitForVisibility(locator);

            new Actions(driver)
                    .moveToElement(element)
                    .perform();

        } catch (TimeoutException e) {
            throw new PageActionException(
                    "Failed to hover over element: " + locator, e
            );
        }
    }

    protected String getText(By locator) {
        try {
            WaitUtils.waitForPageLoad();

            WebElement element = WaitUtils.waitForVisibility(locator);

            String text = element.getText();

            WaitUtils.waitForPageLoad();

            return text;

        } catch (TimeoutException e) {
            throw new PageActionException(
                    "Failed to get text from element: " + locator, e
            );
        }
    }

    protected boolean isDisplayed(By locator) {
        try {
            WaitUtils.waitForPageLoad();

            WebElement element = WaitUtils.waitForVisibility(locator);

            boolean displayed = element.isDisplayed();

            WaitUtils.waitForPageLoad();

            return displayed;

        } catch (TimeoutException e) {
            throw new PageActionException(
                    "Failed to check element visibility: " + locator, e
            );
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            WaitUtils.waitForPageLoad();

            boolean present = !driver.findElements(locator).isEmpty();

            WaitUtils.waitForPageLoad();

            return present;

        } catch(TimeoutException e) {
            throw new PageActionException(
                    "Failed to check element presence: " + locator, e
            );
        }
    }
}


