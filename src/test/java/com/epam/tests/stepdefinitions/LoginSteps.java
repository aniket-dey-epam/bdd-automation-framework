package com.epam.tests.stepdefinitions;

import com.epam.framework.config.ConfigReader;
import com.epam.framework.driver.DriverManager;
import com.epam.framework.impl.DashboardPageImpl;
import com.epam.framework.impl.LoginPageImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private LoginPageImpl loginPage;
    private DashboardPageImpl dashboardPage;

    String baseUrl = ConfigReader.getProperty("baseUrl");

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        DriverManager.getDriver().get(baseUrl + "/login");
        loginPage = new LoginPageImpl();
    }

    @When("the user logs in with username {string} and password {string}")
    public void the_user_logs_in(String username, String password) {
        loginPage.login(username, password);
        dashboardPage = new DashboardPageImpl();
    }

    @Then("the user should see the login success message for {string}")
    public void the_user_should_see_login_success_message(String username) {
        DashboardPageImpl.verifyLoginSuccessMessage(dashboardPage.getSuccessMessage(), username);
    }
}