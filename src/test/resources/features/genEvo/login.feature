Feature: This feature file contains login scenarios for GenEvo application.

  Background:

  Scenario Outline: Validate user can able to login to the application with valid credentials.
    Given login to GenEvo application with username '<username>' and password '<password>'

    @TestCase01
    Examples: Login with valid credentials.
      | username | password    |
      | student  | Password1 |