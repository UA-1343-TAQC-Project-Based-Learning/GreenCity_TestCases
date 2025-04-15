Feature: News Basic Preview

  @Issue("56")
  Background:
    Given The user is on the Create News page as a logged-in user

  Scenario: Verify that the user can preview news content after entering valid data and that the preview matches the input
    When the user enters "Test Preview" into TitleInputTextField
    And the user types "This is a test preview content" in the News Content field
    And the user clicks Preview button on CreateEditNewsPage
    Then the News Title "Test Preview" presents on the Preview News Page
    And the News ContentText "This is a test preview content" presents on the Preview News Page
    And the preview page displays the current date
    And the author's name is displayed correctly
    And the Back to editing link is available for returning to the edit mode