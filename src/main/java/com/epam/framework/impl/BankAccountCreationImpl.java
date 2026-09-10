package com.epam.framework.impl;

import com.epam.framework.base.BasePage;
import com.epam.framework.driver.DriverManager;
import com.epam.framework.pages.BankAccountCreationPage;
import com.epam.framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class BankAccountCreationImpl extends BasePage {

    public BankAccountCreationImpl() {
        super(DriverManager.getDriver());
    }

    public void enterName(String name) {
        type(BankAccountCreationPage.NAME_FIELD, name);
    }

    public void enterBalance(String balance) {
        type(BankAccountCreationPage.BALANCE_FIELD, balance);
    }

    public void selectUser(String username) {
        WebElement dropdown = WaitUtils.waitForVisibility(BankAccountCreationPage.USER_DROPDOWN);
        Select userSelect = new Select(dropdown);
        userSelect.selectByVisibleText(username);
    }

    public void clickCreate() {
        click(BankAccountCreationPage.CREATE_ACCOUNT_BUTTON);
    }

    public void createAccount(String name, String balance, String username) {
        enterName(name);
        enterBalance(balance);
        selectUser(username);
        clickCreate();
    }

    public boolean isAccountInList(String accountName) {
        By accountRow = By.xpath("//td[contains(text(),'" + accountName + "')]");
        try {
            WaitUtils.waitForVisibility(accountRow);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void verifyAccountInList(String accountName) {
        boolean found = isAccountInList(accountName);
        Assert.assertTrue(found, "Newly created bank account '" + accountName + "' was not found in the list");
    }
}