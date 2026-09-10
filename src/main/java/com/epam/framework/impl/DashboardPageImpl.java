package com.epam.framework.impl;

import com.epam.framework.base.BasePage;
import com.epam.framework.driver.DriverManager;
import com.epam.framework.pages.DashboardPage;
import org.testng.Assert;

public class DashboardPageImpl extends BasePage {

    public DashboardPageImpl() {
        super(DriverManager.getDriver());
    }

    public String getSuccessMessage() {
        return getText(DashboardPage.SUCCESS_MESSAGE);
    }

    public static void verifyLoginSuccessMessage(String actual, String username) {
        String expected = String.format("You are logged in as user \"%s\".", username);
        Assert.assertEquals(actual, expected);
    }
}