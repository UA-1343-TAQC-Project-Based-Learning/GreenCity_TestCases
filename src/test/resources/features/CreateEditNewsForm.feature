@create_edit_news_form
Feature: Checking some functionalities in the "Create, Edit News" Form

  Background:
    Given The user is on the Home page as a logged-in user


  @test_case#13
  Scenario: test_case#13: Verify that the "Create News" form displays all the necessary fields in the correct order
    When the user navigates to the GreenCity News page
    And the user navigates to the Create News page
    Then the form should contain the following fields in order
    And the form should contain the following buttons:
      | Cancel  |
      | Preview |
      | Publish |
    And all elements should be in correct order

  @test_case#103
  Scenario: test_case#103: Verify that an image larger than 10MB is not accepted
    When the user navigates to the GreenCity News page
    And the user navigates to the Create News page
    And the user uploads the image from "images/GreenCity11mb.png"
    Then verify the image is not uploaded
    And verify size validation message should present

  @test_case#99
  Scenario: test_case#99: Verify that the author can edit their own news and the changes are saved
    When the user navigates to the Eco News page
    And store date of creation card as "creationCardDate"
    And store first card title as "cardTitle"
    And generate unique title and store as "newTitle"
    And go to the "cardTitle" News Card page
    And go to the Edit News Card Page
    And the user types stored value "newTitle" in the Title field
    And the user types "updating a content input" in the Content field
    And the user clicks only unselected tag filter button "Events"
    And the user clicks only unselected tag filter button "News"
    And the user clicks the Publish button
    And go to the "newTitle" News Card page
    And store title in the News Card Page as "newChangedCardTitle"
    Then the stored "newTitle" title should be equal to stored "newChangedCardTitle" title
    And the content field should be updated
    And the card should contain the following tag filters:
      | News   |
      | Events |
    When store date in the News Card Page as "cardDate"
    Then the stored "creationCardDate" date should be equal to stored "cardDate" date