Feature: Onboarding

 @web
  Scenario Outline: create Deposit one 
    Given launch browser
    When user navigates to the URL  <user>
    Then user click on login
      | ProductLine | ProductType          | CustomerType | ProductName                   |
      | Deposit     | Money Market Account | Consumer     | Consumer Money Market Account |

    Examples: 
      | s |
      | s |
     
@web
  Scenario: create Loan Deposit
    Given launch browsert
    When user navigates to the URL  <user>
    Then user click on login
      | ProductLine | ProductType | CustomerType | ProductName                       |
      | Loan        | Equipment   | Commercial   | Commercial Small Ticket Equipment |
@web3
  Scenario: create customer Deposit
    Given launch browsert
    When user navigates to the URL  <user>
    Then user click on login
      | ProductLine | ProductType | CustomerType | ProductName                       |
      | Loan        | Equipment   | Commercial   | Commercial Small Ticket Equipment |
@web4
  Scenario: create Customer
    Given launch browsert
    When user navigates to the URL  <user>
    Then user click on login
      | ProductLine | ProductType | CustomerType | ProductName                       |
      | Loan        | Equipment   | Commercial   | Commercial Small Ticket Equipment |
