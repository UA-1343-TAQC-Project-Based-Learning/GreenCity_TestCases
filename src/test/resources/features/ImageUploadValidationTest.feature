Feature: New EcoNews Image Uploading

  Background:
    Given The user is on the Create News page as a logged-in user

    Scenario: Verify the validation of the "Upload Image" field (accepted formats: PNG/JPG, maximum size: 10MB).
      When the user uploads the image from "images/GreenCity5mb.png"
      Then the presentation window displayed on the uploaded image

      When the user clicks the Cancel button
      And the user uploads the image from "images/GreenCity1mb.gif"
      Then the image upload warning message: "The image isn't uploaded" should be displayed
      And the background color of the image dropzone field should be highlighted in red

      When the user uploads the image from "images/GreenCity15mb.jpg"
      Then the background color of the image dropzone field should be highlighted in red
      And the image upload warning message: "The image isn't uploaded" should be displayed