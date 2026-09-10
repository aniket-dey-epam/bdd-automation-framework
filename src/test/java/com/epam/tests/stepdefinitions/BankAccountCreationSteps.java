package com.epam.tests.stepdefinitions;

import com.epam.framework.driver.DriverManager;
import com.epam.framework.impl.BankAccountCreationImpl;
import com.epam.framework.impl.LoginPageImpl;
import com.epam.framework.config.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BankAccountCreationSteps {

    private BankAccountCreationImpl bankAccountPage;

    String baseUrl = ConfigReader.getProperty("baseUrl");

    @Given("the user is logged in as {string} with password {string}")
    public void the_user_is_logged_in(String username, String password) {

        DriverManager.getDriver().get(baseUrl + "/login");
        new LoginPageImpl().login(username, password);
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));
    }

    @When("the user navigates to the create bank account page")
    public void the_user_navigates_to_create_bank_account_page() {
        DriverManager.getDriver().get(baseUrl + "/bank-account/new");
        bankAccountPage = new BankAccountCreationImpl();
    }

    @When("creates a bank account with name {string}, balance {string} and user {string}")
    public void creates_a_bank_account(String name, String balance, String user) {
        bankAccountPage.createAccount(name, balance, user);
    }

    @Then("the new bank account {string} should appear in the bank account list")
    public void the_new_bank_account_should_appear(String accountName) {
        DriverManager.getDriver().get(baseUrl + "/bank-account");
        bankAccountPage.verifyAccountInList(accountName);
    }
}