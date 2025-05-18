Feature: Leaves Search functionality
  @Ignore
  Scenario Outline: Valid Leaves Filter
    Given I wait for the leaves page to be available
    Then I enter To date "<ToDate>"
    And I enter From date "<FromDate>"
    And I enter show leaves status as "<Status>"
    And I click search button
    Then I wait for search data to be available
    Then I verify the search results

    Examples:
      | FromDate | ToDate | Status  |
      | Admin    | admin123 | taken |