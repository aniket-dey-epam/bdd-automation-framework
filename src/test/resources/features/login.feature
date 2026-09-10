@login
Feature: Login

  Scenario Outline: Successful login with valid credentials
    Given the user is on the login page
    When the user logs in with username "<username>" and password "<password>"
    Then the user should see the login success message for "<username>"

    Examples:
      | username | password |
      | admin    | admin    |
      | user     | user     |
      | aniket   | aniket2002 |
