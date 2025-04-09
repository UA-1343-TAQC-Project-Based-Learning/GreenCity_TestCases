Feature: News Tag Selection
  As a user
  I want to select appropriate tags when creating news
  So that my news is properly categorized

  Background:
    Given The user is on Create News page
    And The user fills required fields with valid data

  Scenario Outline: Create news with a valid number of tags
    When The user selects the following tags:
      | <tag>     |
    And The user clicks "Publish" button
    Then The news should be published with the selected tags

    Examples:
      | tag       |
      | News      |
      | Events    |
      | Education |

  Scenario: Create news with multiple tags
    When The user selects the following tags:
      | News      |
      | Events    |
      | Education |
    And The user clicks "Publish" button
    Then The news should be published with the selected tags
