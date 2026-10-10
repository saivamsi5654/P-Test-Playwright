Feature: This feature file contains login scenarios for orangeHRM application.

  Background:

  Scenario Outline: Validate user can able to login to the application with valid credentials.
    Given login to application with username '<username>' and password '<password>'
    Examples:
      | username | password |
      | Admin    | admin123 |