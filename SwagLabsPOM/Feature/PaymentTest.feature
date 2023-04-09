#Author: https://github.com/aguirams
#Scenario Descrition: These scenarios will test the functionality of payment for items added to cart

Feature: Payment Test

  @PaymentTest
  Scenario Outline: Add to Cart from Inventory Page
    Given User is logged in to Swag Labs
    When User clicks the Add to Cart button for item "<itemName>"
    Then Inventory item "<itemName>" is added to cart
    When User goes to cart page
    Then Added item "<itemName>" is in the cart
    When User clicks the Checkout button
    Then Checkout page is displayed
    When User clicks the Cancel button
    Then User is redirected to cart page with item "<itemName>" still in cart

    Examples: 
      | itemName              |
      | Sauce Labs Bike Light |

  @PaymentTest
  Scenario Outline: Add to Cart from Inventory Page
    Given User is logged in to Swag Labs
    When User clicks the Add to Cart button for item "<itemName>"
    Then Inventory item "<itemName>" is added to cart
    When User goes to cart page
    Then Added item "<itemName>" is in the cart
    When User clicks the Checkout button
    Then Checkout page is displayed
    When User inputs First Name "<firstName>" Last Name "<lastName>" Postal "<postCode>"
    And User clicks the Continue button
    Then Checkout Overview page is diplayed with item "<itemName>" details
    When User clicks the Finish button
    Then Order is completed

    Examples: 
      | itemName              | firstName  | lastName  | postCode |
      | Sauce Labs Bike Light | First Name | Last Name |     1234 |

  @PaymentTest
  Scenario Outline: Add to Cart from Inventory Page
    Given User is logged in to Swag Labs
    When User clicks the Add to Cart button for item "<itemName>"
    Then Inventory item "<itemName>" is added to cart
    When User goes to cart page
    Then Added item "<itemName>" is in the cart
    When User clicks the Checkout button
    Then Checkout page is displayed
    When User inputs only Last Name "<lastName>" Postal "<postCode>"
    And User clicks the Continue button
    Then error message for first name is displayed
    When User inputs only First Name "<firstName>" Postal "<postCode>"
    And User clicks the Continue button
    Then error message for last name is displayed
    When User inputs only First Name "<firstName>" Last Name "<lastName>"
    And User clicks the Continue button
    Then error message for postal code is displayed

    Examples: 
      | itemName            | firstName | lastName | postCode |
      | Sauce Labs Backpack | Samson    | Delilah  |     1234 |
