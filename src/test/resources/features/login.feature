@Login
Feature: Login functionality

  Background:
    Given I wait for the login form to be available

  Rule: Business rule 1
    @Ignore
    Scenario: Valid login
      Given I am at login page
      Then I enter username
      And I enter password
      And I click submit button
      Then I wait for dashboard to be loaded
      Then I verify the dashboard URL
      Then I verify the cookie is set
      Then I verify the heading
      Then  I verify User DropDown

#    Examples:
#      | username | password |
#      | Admin    | admin123 |
#  @chrome
#  Examples:
#    | username | password |
#    | Admin1   | admin123 |

    @Ignore
    Scenario Outline: Valid login with example
      Given I am at login page
      Then I enter username from example "<username>"
      And I enter password from example "<password>"
      And I click submit button
      Then I wait for dashboard to be loaded
      Then I verify the dashboard URL
      Then I verify the cookie is set
      Then I verify the heading
      Then  I verify User DropDown

      Examples:
        | username | password |
        | Admin    | admin123 |
#  @chrome
#  Examples:
#    | username | password |
#    | Admin1   | admin123 |
    @Ignore
    Scenario: Valid login with example
      Given I am at login page
      Then I enter username from example "<username>"
      And I enter password from example "<password>"
      And I add following items to cart:
      |items | count|
      |apples | 10  |
      |banana | 20  |
      And I click submit button
      Then I wait for dashboard to be loaded
      Then I verify the dashboard URL
      Then I verify the cookie is set
      Then I verify the heading
      Then  I verify User DropDown

    @Ignore
    Scenario: Valid login with example
      Given I am at login page
      Then I enter username from example "<username>"
      And I enter password from example "<password>"
      And I add following items to cart as map:
        |item | count| type|
        |apples | 10  | china|
        |banana | 20  | equador|
      And I click submit button
      Then I wait for dashboard to be loaded
      Then I verify the dashboard URL
      Then I verify the cookie is set
      Then I verify the heading
      Then  I verify User DropDown

    @Ignore
    Scenario: Valid login with example
      Given I am at login page
      Then I enter username from example "<username>"
      And I enter password from example "<password>"
      And I add following items to cart as list of list of string:
        |apples | 10  | china|
        |banana | 20  | equador|
      And I click submit button
      Then I wait for dashboard to be loaded
      Then I verify the dashboard URL
      Then I verify the cookie is set
      Then I verify the heading
      Then  I verify User DropDown
    @Ignore
    Scenario: Valid login with example
      Given I am at login page
      Then I enter username from example "<username>"
      And I enter password from example "<password>"
      And I add following items to cart as single map:
        |fruit | banana  |
        |count | 20  |
      And I click submit button
      Then I wait for dashboard to be loaded
      Then I verify the dashboard URL
      Then I verify the cookie is set
      Then I verify the heading
      Then  I verify User DropDown

    @Ignore
    Scenario: Valid login with example
      Given I am at login page
      Then I enter username from example "<username>"
      And I enter password from example "<password>"
      And I add following items to cart as map of list:
        |fruit | banana  | abc|
        |count | 20  |def     |
      And I click submit button
      Then I wait for dashboard to be loaded
      Then I verify the dashboard URL
      Then I verify the cookie is set
      Then I verify the heading
      Then  I verify User DropDown

    @Ignore
    Scenario: Valid login with example
      Given I am at login page
      Then I enter username from example "<username>"
      And I enter password from example "<password>"
      And I give the route:
        """
        {
        "origin":"RUH",
        "destination":"JED"
        }
        """
      And I click submit button
      Then I wait for dashboard to be loaded
      Then I verify the dashboard URL
      Then I verify the cookie is set
      Then I verify the heading
      Then  I verify User DropDown


    @Ignore
    Scenario: Valid login with example
      Given I am at login page
      Then I enter username from example "<username>"
      And I enter password from example "<password>"
      And I add following items to cart as multiple:
        |items |
        |apples |
        |banana |
      And I click submit button
      Then I wait for dashboard to be loaded
      Then I verify the dashboard URL
      Then I verify the cookie is set
      Then I verify the heading
      Then  I verify User DropDown


  Rule: Busines rule 2

    @Ignore
    Scenario Outline: Invalid login
      Given I am at login page
      Then I enter username "<username>"
      And I enter password "<password>"
      And I click submit button
      Then I verify that page is not loged in and proper error message is displayed

      Examples:
        | username | password |
        | Admin    | admin    |
    @Ignore
      Scenario: Testing
        Given I am at login page
        Then I use parameters
        |apple|
        |banana|
    @Ignore
    Scenario: Testing Map
      Given I am at login page
      Then I use parameters as map
        |apple| papple|
        |banana| pap1 |
        |strawberry|pap2|

    @Ignore
    Scenario: Testing List
      Given I am at login page
      Then I use parameters as list
        |apple| papple|
        |banana| pap1 |
        |strawberry|pap2|

      Scenario: route test
        Given I am at login page
        Then I use route class:
        |origin|destination|
        |RUH   |JED        |

