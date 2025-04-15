@edit_news
Feature: News Editing Functionality
  As a news author
  I want to manage my news posts
  So that I can keep my content up-to-date

  Background:
    Given The logged in user created and navigated to their news post

  @TC97
  @edit_button_visible
  Scenario: Verify Edit news button is visible to author
    Then The "Edit news" button should be visible
    And The button text should be "Edit news"
    When The user clicks the "Edit news" button
    Then The edit form header should be "Edit news"

  @TC101
  @empty_title
  Scenario: Verify news cannot be submitted without title
    When The user opens the edit form for their news
    And The user clears the title field
    And The user clicks outside the title field
    Then The title field border should be red
    And The "Edit" button should be disabled
    When The user cancels the edit
    Then The original news title should remain unchanged

  @TC104
  @cancel_editing
  Scenario: Verify canceling editing discards changes
    When The user opens the edit form for their news
    And The user edits the news title
    And The user cancels the edit
    Then The user should be redirected to Eco News page
    And The original news title should be displayed

  @TC102
    @short_content_is_not_accepted
  Scenario Outline: Verify that content shorter than 20 characters is not accepted
    When The user clicks the "Edit news" button
    And The user enters "<text>" into the content field
    Then The warning message should say "Not enough characters. Left: <expected>"
    And The "Publish" button should be disabled
    When The user cancels the edit
    And Clean up test news

    Examples:
      | text                | expected |
      | <SPACE>             | 19       |
      | T                   | 19       |
      | TestString_19_Chars | 1        |
      | 1                   | 19       |
      | 10 chars!!          | 10       |
      | !                   | 19       |
