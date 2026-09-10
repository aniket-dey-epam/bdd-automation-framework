package com.epam.tests.stepdefinitions;

import com.epam.framework.config.ConfigReader;
import com.epam.framework.driver.DriverManager;
import com.epam.framework.impl.RegistrationPageImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationSteps {

    private RegistrationPageImpl registrationPage;

    String baseUrl = ConfigReader.getProperty("baseUrl");

    @Given("the user is on the registration page")
    public void the_user_is_on_the_registration_page() {
        DriverManager.getDriver().get(baseUrl + "/account/register");
        registrationPage = new RegistrationPageImpl();
    }

    @When("the user registers with username {string}, email {string}, password {string} and confirm password {string}")
    public void the_user_registers(String username, String email, String password, String confirmPassword) {
        registrationPage.register(username, email, password, confirmPassword);
    }

    @Then("the user should see a message containing {string}")
    public void the_user_should_see_message_containing(String partialMessage) {
        RegistrationPageImpl.verifySuccessMessageContains(registrationPage.getSuccessMessage(), partialMessage);
    }
}