#Author: https://github.com/aguirams
#Scenario Descrition: These scenarios will test the login functionality

Feature: Login Action

  @LoginTest
  Scenario Outline: Successful Login with Valid Credentials
    Given User is on Swag Labs Home Page
    When User logs in with UserName as "<username>" and Password as "<password>"
    Then User is redirected to Inventory Page

    #Then Message displayed Login Successfully
    #When User LogOut from the Application
    #Then Message displayed Logout Successfully
    Examples: 
      | username      | password     |
      | standard_user | secret_sauce |

  @LoginTest
  Scenario Outline: Unsuccessful Login with Invalid Credentials
    Given User is on Swag Labs Home Page
    When User logs in with UserName as "<username>" and Password as "<password>"
    Then Error message "<errorMessage>" is displayed

    Examples: 
      | username        | password     | errorMessage                                                              |
      |                 |              | Epic sadface: Username is required                                        |
      | standard_user   |              | Epic sadface: Password is required                                        |
      | standard_user   | wrongpass    | Epic sadface: Username and password do not match any user in this service |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |

  @LoginTest
  Scenario Outline: Unsuccessful Login with Invalid Credentials
    Given User is on Swag Labs Home Page
    When User redirects to inventory page without logging in
    Then Error message "<errorMessage>" is displayed

    Examples: 
      | errorMessage |
      | Special      |
