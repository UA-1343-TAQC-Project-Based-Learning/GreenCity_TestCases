Feature: Create News Form Display

  As a registered GreenCity user
  I want to verify that the "Create News" form displays all necessary fields in the correct order
  So that I can ensure a proper user experience

  Background:
    Given the user is registered and logged into the GreenCity system

  Scenario: Verify Create News form displays all necessary fields in the correct order
    When the user navigates to the GreenCity News page
    And click the "Create News" button
    Then the Create News form should be displayed
    And the form should contain the following fields in order
    And the form should contain the following buttons:
      | Button   |
      | Cancel   |
      | Preview  |
      | Publish  |
    And the Author field should be pre-filled and non-editable
    And the Date field should be pre-filled with the current date and non-editable
