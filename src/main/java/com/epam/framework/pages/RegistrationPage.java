package com.epam.framework.pages;

import org.openqa.selenium.By;

public class RegistrationPage {

    public static By USERNAME_FIELD = By.cssSelector("input[data-cy='username']");
    public static By EMAIL_FIELD = By.cssSelector("input[data-cy='email']");
    public static By PASSWORD_FIELD = By.cssSelector("input[data-cy='firstPassword']");
    public static By CONFIRM_PASSWORD_FIELD = By.cssSelector("input[data-cy='secondPassword']");
    public static By REGISTER_BUTTON = By.cssSelector("button[jhitranslate='register.form.button']");
    public static By REGISTRATION_SUCCESS_MESSAGE = By.cssSelector("div[jhitranslate='register.messages.success']");

}
