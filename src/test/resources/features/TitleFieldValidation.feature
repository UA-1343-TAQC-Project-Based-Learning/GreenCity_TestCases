Feature: Title Field Validation

  Verify the validation of the "Title" field (mandatory, maximum 170 characters)
  and that the "Publish" button remains disabled
  until both Title and Main Text (Content) fields are filled.

  Background:
    Given The user is on the Create News page as a logged-in user
    
    Scenario: Verify the validation of the "Title" field
      When the user leaves the Title field empty
      Then the Title field's border is highlighted in red
      Then the Publish button is disabled
      Then the character counter shows "0/170"

      And the user enters 171 character-long string into the Title field
      Then the text is truncated to 170 characters
      Then the counter is highlighted in red when exceeding the limit

      And the user enters "Test News" into TitleInputTextField
      Then the character counter shows "9/170"
      Then the Title field's border is grey
      Then the Publish button is disabled

      And the user clicks the "News" tag on the Create News page
      Then the Publish button is disabled

      And the user types "Test Text Field Content" in the News Content field
      Then the Publish button is enabled
