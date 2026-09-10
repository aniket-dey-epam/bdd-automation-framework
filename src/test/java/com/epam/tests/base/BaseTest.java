package com.epam.tests.base;

import com.epam.framework.config.ConfigReader;
import com.epam.framework.driver.BrowserType;
import com.epam.framework.driver.DriverFactory;
import com.epam.framework.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @Before
    public void setUp(Scenario scenario) {

        log.info("Starting scenario: {}", scenario.getName());

        ConfigReader.loadProperties();

        BrowserType browserType =
                BrowserType.valueOf(
                        ConfigReader.getProperty("browser").toUpperCase()
                );

        log.info("Launching browser: {}", browserType);

        DriverManager.setDriver(
                DriverFactory.getDriver(browserType)
        );

        DriverManager.getDriver()
                .manage()
                .window()
                .maximize();

        String baseUrl = ConfigReader.getProperty("baseUrl");
        log.info("Navigating to base URL: {}", baseUrl);

        DriverManager.getDriver()
                .get(baseUrl);
    }

    @After
    public void tearDown(Scenario scenario) {

        log.info("Finished scenario: {} | Status: {}", scenario.getName(), scenario.getStatus());

        if (scenario.isFailed()) {
            WebDriver driver = DriverManager.getDriver();
            if (driver instanceof TakesScreenshot screenshotDriver) {
                log.warn("Scenario failed, attaching screenshot: {}", scenario.getName());
                byte[] screenshot = screenshotDriver.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failure Screenshot");
            }
        }

        if (DriverManager.getDriver() != null) {
            log.info("Quitting driver for scenario: {}", scenario.getName());
            DriverManager.getDriver().quit();
            DriverManager.unsetDriver();
        }
    }
}