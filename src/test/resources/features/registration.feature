@registration
Feature: Registration

  Scenario Outline: Successful registration with valid details
    Given the user is on the registration page
    When the user registers with username "<username>", email "<email>", password "<password>" and confirm password "<confirmPassword>"
    Then the user should see a message containing "Registration saved!"

    Examples:
      | username | email                      | password   | confirmPassword |
      | aniket   | useranonymousvi6@gmail.com | aniket2002 | aniket2002       |
      | alex     | alexstuart400@gmail.com    | alex666    | alex666          |
