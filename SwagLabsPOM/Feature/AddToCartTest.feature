#Author: https://github.com/aguirams
#Scenario Descrition: These scenarios will test the functionality of adding to and removing from cart

Feature: Add to Cart Test

  @AddToCartTest
  Scenario Outline: Add to Cart from Inventory Page
    Given User is logged in to Swag Labs
    When User clicks the Add to Cart button for item "<itemName>"
    Then Inventory item "<itemName>" is added to cart
    When User goes to cart page
    Then Added item "<itemName>" is in the cart
    When User removes item "<itemName>" from cart
    Then Item "<itemName>" previously added to cart is removed

    Examples: 
      | itemName              |
      | Sauce Labs Bike Light |

  @AddToCartTest
  Scenario Outline: Add to Cart from Inventory Page
    Given User is logged in to Swag Labs
    And User clicks on the inventory item "<itemName>" name
    When User clicks the Add to Cart button for item "<itemName>" in details page
    Then Inventory item "<itemName>" is added to cart from details page
    When User goes to cart page
    Then Added item "<itemName>" is in the cart
    When User removes item "<itemName>" from cart
    Then Item "<itemName>" previously added to cart is removed

    Examples: 
      | itemName                          |
      | Test.allTheThings() T-Shirt (Red) |

  @AddToCartTest
  Scenario Outline: Add Two Items to Cart
    Given User is logged in to Swag Labs
    When User adds item "<itemName1>" and item "<itemName2>" to cart
    Then Added item "<itemName1>" and item "<itemName2>" are in the cart
    When User removes items "<itemName1>" and "<itemName2>" from cart
    Then Item cart is empty

    Examples: 
      | itemName1                         | itemName2               |
      | Test.allTheThings() T-Shirt (Red) | Sauce Labs Bolt T-Shirt |

  @AddToCartTest
  Scenario Outline: Add to Cart then Immediatly Remove Item from Cart - Inventory Page
    Given User is logged in to Swag Labs
    When User clicks the Add to Cart button for item "<itemName>"
    Then Inventory item "<itemName>" is added to cart
    When Inventory item "<itemName>" is removed from cart in inventory page
    Then Item is not in the cart

    Examples: 
      | itemName          |
      | Sauce Labs Onesie |

  @AddToCartTest
  Scenario Outline: Add to Cart then Immediatly Remove Item from Cart - Details Page
    Given User is logged in to Swag Labs
    And User clicks on the inventory item "<itemName>" name
    When User clicks the Add to Cart button for item "<itemName>" in details page
    Then Inventory item "<itemName>" is added to cart from details page
    When Inventory item "<itemName>" is removed from cart in details page
    Then Item is not in the cart

    Examples: 
      | itemName                 |
      | Sauce Labs Fleece Jacket |
