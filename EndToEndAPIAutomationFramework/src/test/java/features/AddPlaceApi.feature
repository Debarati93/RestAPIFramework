Feature: Validate Add Place API

  @AddPlaceAPI
  Scenario Outline: Verify Add Place API
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceApi" api with http "Post" request
    Then API call got sucess with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "GetPLaceApi"

  Examples:
    | name | language | address |
    |Frontline house| French-IN | 29, side layout, cohen 09 |
#    |Tester| English | 30, side layout, hohen 09 |

  @DeletePlaceAPI
  Scenario: Verify Delete Place API
    Given Delete Place Payload
    When user calls "DeletePLaceApi" api with http "Post" request
    Then API call got sucess with status code 200





