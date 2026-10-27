@PracticePage
Feature: This feature file contains scenarios related to practice page.

  Background:

  Scenario Outline: Validate text input logic in fom controls.
    Given login to GenEvo application with username '<username>' and password '<password>'
    And Navigate to practice page
    And Navigate to '<module>' module
    And Navigate to '<submodule>' submodule
    When Enter name '<name>', email '<email>' and address '<address>'
    Then Validate text input status with '<status>'

    Examples: Practice Form Controls text input part with valid data.
      | username | password    | module        | submodule   | name          | email           | address | status    |
      | student  | Password123 | form-controls | text-inputs | Vamsi Krishna | vamsi@gmail.com | kadapa  | submitted |

    Examples: Practice Form Controls text input part with invalid data.
      | username | password    | module        | submodule   | name          | email | address | status  |
      | student  | Password123 | form-controls | text-inputs | Vamsi Krishna | vamsi | kadapa  | invalid |