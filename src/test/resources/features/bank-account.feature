@bankAccount
Feature: Create Bank Account

  Scenario: Successfully create a new bank account
    Given the user is logged in as "admin" with password "admin"
    When the user navigates to the create bank account page
    And creates a bank account with name "Test Account", balance "1000" and user "admin"
    Then the new bank account "Test Account" should appear in the bank account list
