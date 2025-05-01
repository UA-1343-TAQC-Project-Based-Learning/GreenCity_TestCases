Feature: Restrict editing of news posts by not logged-in users
  @Issue("98")
  As a not logged-in users
  I should not be able to see the "Edit news" button
  on posts created by other logged users

  Scenario: not logged-in users  can not see the Edit news button
    When the user navigates to the Eco News page
    And the user opens the news post with title "Company Update"
    Then the user should not see the Edit news button
