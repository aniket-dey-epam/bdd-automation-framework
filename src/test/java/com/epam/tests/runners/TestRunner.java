package com.epam.tests.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.epam.tests.stepdefinitions", "com.epam.tests.base"},
        plugin = {
                "pretty",
                "html:reports/cucumber/cucumber.html",
                "json:reports/cucumber/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {
}