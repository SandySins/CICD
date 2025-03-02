
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page
	
  @Regression
  Scenario Outline: Positive Test of Submitting the Order
    Given I Logged in with Username <name> and Password <password>
    When I add the product <productName> to cart
    And Checkout <productName> and submit order
    Then "Thankyou for the order." message is displayed on confirmation page

    Examples: 
      | name  								| password 		|	productName |
      | sunny938650@gmail.com | Kratos@1234 | ZARA COAT 3 |
