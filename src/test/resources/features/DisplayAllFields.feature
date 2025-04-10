Feature: Checking the "Create News" Form Display with All Fields

  Background:
    Given The user is on the Home page as a logged-in user


    @test_case#13
    Scenario: test_case#13: Verify that the "Create News" form displays all the necessary fields in the correct order
      When the user navigates to the GreenCity News page
      And the user navigates to the Create News page
      Then the form should contain the following fields in order
      And the form should contain the following buttons:
        | Cancel   |
        | Preview  |
        | Publish  |
      And all elements should be in correct order

