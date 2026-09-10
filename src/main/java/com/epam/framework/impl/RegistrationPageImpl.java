package com.epam.framework.impl;

import com.epam.framework.base.BasePage;
import com.epam.framework.driver.DriverManager;
import com.epam.framework.pages.RegistrationPage;
import org.testng.Assert;

public class RegistrationPageImpl extends BasePage {

    public RegistrationPageImpl() {
        super(DriverManager.getDriver());
    }

    public void enterUsername(String username) {
        type(RegistrationPage.USERNAME_FIELD, username);
    }

    public void enterEmail(String email) {
        type(RegistrationPage.EMAIL_FIELD, email);
    }

    public void enterPassword(String password) {
        type(RegistrationPage.PASSWORD_FIELD, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        type(RegistrationPage.CONFIRM_PASSWORD_FIELD, confirmPassword);
    }

    public void clickRegister() {
        click(RegistrationPage.REGISTER_BUTTON);
    }

    public void register(String username, String email, String password, String confirmPassword) {
        enterUsername(username);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        clickRegister();
    }

    public String getSuccessMessage() {
        return getText(RegistrationPage.REGISTRATION_SUCCESS_MESSAGE);
    }

    public static void verifySuccessMessageContains(String actual, String partialMessage) {
        Assert.assertTrue(actual.contains(partialMessage));
    }
}