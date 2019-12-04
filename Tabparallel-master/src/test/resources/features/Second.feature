Feature: E-Login


    @smOKE
 Scenario Outline: Create lona Product
    Given launch browsert
    When user navigates to the URL  <user>
    Then user click on login
      | ProductLine | ProductType | CustomerType | ProductName                       |
      | Loan        | Equipment   | Commercial   | Commercial Small Ticket Equipment |
Examples:
|user|
|qa.abhay.ent02|
|qa.formcal.user| 
 