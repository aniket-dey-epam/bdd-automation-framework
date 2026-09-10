package com.epam.framework.pages;

import org.openqa.selenium.By;

public class LoginPage {

    public static By USERNAME_FIELD = By.id("username");
    public static By PASSWORD_FIELD = By.id("password");
    public static By LOGIN_BUTTON = By.cssSelector("button[jhitranslate='login.form.button']");
    public static By LOGIN_ERROR_MESSAGE = By.cssSelector("div[jhitranslate='login.messages.error.authentication']");

}
