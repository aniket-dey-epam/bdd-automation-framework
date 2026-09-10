# BDD Automation Framework

Minimal Selenium + Cucumber + TestNG framework, CLI-driven via Maven.

## Stack
- Java 21
- Selenium 4.35.0
- Cucumber-JVM 7.20.1
- TestNG 7.11.0 (execution engine, via `cucumber-testng`)

## Structure
- `pages/` — Page Objects (unchanged Selenium logic)
- `driver/` — `DriverFactory` (creates the WebDriver) + `DriverManager` (thread-safe holder)
- `stepdefinitions/` — Gherkin step implementations
- `hooks/` — `@Before` / `@After` driver setup & teardown per scenario
- `runners/` — `TestRunner`, the Cucumber-TestNG entry point
- `features/` — Gherkin `.feature` files

## Run from CLI

```bash
# run everything
mvn test

# run a single feature via tag
mvn test -Dcucumber.filter.tags="@login"
mvn test -Dcucumber.filter.tags="@registration"
mvn test -Dcucumber.filter.tags="@bankAccount"

# combine tags
mvn test -Dcucumber.filter.tags="@login or @registration"
```

Reports are written to `reports/cucumber/cucumber.html` and `cucumber.json` after each run.

## Not included yet (intentionally, for a bare-minimum setup)
- Config/properties-based environment switching (`config/`, `.properties` files)
- Logging, screenshots-on-failure, JSON test data, custom exceptions, TestNG listeners

These can be layered in later without touching the BDD wiring above.
