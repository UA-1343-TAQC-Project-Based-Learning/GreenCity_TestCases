Feature: Title Field Validation

  Verify the validation of the "Title" field (mandatory, maximum 170 characters)
  and that the "Publish" button remains disabled
  until both Title and Main Text (Content) fields are filled.

  @Issue("14")
  Background:
    Given The user is on the Create News page as a logged-in user
    
    Scenario: Verify the validation of the "Title" field
      When the user leaves the Title field empty
      Then the Title field's border is highlighted in red
      And the Publish button is disabled
      And the character counter shows "0/170"

      When the user enters 171 character-long string into the Title field
      Then the text is truncated to 170 characters
      And the counter is highlighted in red when exceeding the limit

      When the user enters "Test News" into TitleInputTextField
      Then the character counter shows "9/170"
      And the Title field's border is grey
      And the Publish button is disabled

      When the user clicks the "News" tag on the Create News page
      Then the Publish button is disabled

      When the user types "Test Text Field Content" in the News Content field
      Then the Publish button is enabled


