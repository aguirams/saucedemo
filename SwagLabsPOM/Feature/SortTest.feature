#Author: https://github.com/aguirams
#Scenario Descrition: These scenarios will test the functionality of sorting the items in the inventory page

Feature: Sort Test

  @SortTest
  Scenario: Sort Inventory Items from A to Z
  Given User is logged in to Swag Labs
  And Swag Labs Inventory Page is displayed
  When User sorts the items from A to Z
  Then Inventory items are sorted from A to Z
  
  @SortTest
  Scenario: Sort Inventory Items from Z to A
  Given User is logged in to Swag Labs
  And Swag Labs Inventory Page is displayed
  When User sorts the items from Z to A
  Then Inventory items are sorted from Z to A
  
  @SortTest
  Scenario: Sort Inventory Items from low to high price
  Given User is logged in to Swag Labs
  And Swag Labs Inventory Page is displayed
  When User sorts the items from low to high price
  Then Inventory items are sorted from low to high price
  
  @SortTest
  Scenario: Sort Inventory Items from high to low price
  Given User is logged in to Swag Labs
  And Swag Labs Inventory Page is displayed
  When User sorts the items from high to low price
  Then Inventory items are sorted from high to low price
    
