package com.epam.framework.impl;

import com.epam.framework.base.BasePage;
import com.epam.framework.driver.DriverManager;
import com.epam.framework.pages.LoginPage;

public class LoginPageImpl extends BasePage {

    public LoginPageImpl() {
        super(DriverManager.getDriver());
    }

    public void enterUsername(String username) {
        type(LoginPage.USERNAME_FIELD, username);
    }

    public void enterPassword(String password) {
        type(LoginPage.PASSWORD_FIELD, password);
    }

    public void clickLogin() {
        click(LoginPage.LOGIN_BUTTON);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isAuthenticationErrorDisplayed() {
        return isElementPresent(LoginPage.LOGIN_ERROR_MESSAGE);
    }

    public String getErrorMessage() {
        return getText(LoginPage.LOGIN_ERROR_MESSAGE);
    }
}