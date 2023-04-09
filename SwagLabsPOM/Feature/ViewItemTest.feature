#Author: https://github.com/aguirams
#Scenario Descrition: These scenarios will test the functionality of viewing item by clicking its name or image

Feature: View Items Test

  @ViewItemsTest
  Scenario Outline: View item by clicking item name
    Given User is logged in to Swag Labs
    When User clicks on the inventory item "<itemName>" name
    Then User is redirected to details page for item "<itemName>" with price "<itemPrice>"

    Examples: 
      | itemName            | itemPrice |
      | Sauce Labs Backpack | $29.99    |

  @ViewItemsTest
  Scenario Outline: View item by clicking item image
    Given User is logged in to Swag Labs
    When User clicks on the inventory item "<itemName>" image
    Then User is redirected to details page for item "<itemName>" with price "<itemPrice>"

    Examples: 
      | itemName                          | itemPrice |
      | Test.allTheThings() T-Shirt (Red) | $15.99    |
