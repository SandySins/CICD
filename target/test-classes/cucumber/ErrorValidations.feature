
@tag
Feature: Error Validations
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    And I Logged in with Username <name> and Password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name  								| password 		
      | sunny938ds650@gmail.com | Kratossd@1234 
