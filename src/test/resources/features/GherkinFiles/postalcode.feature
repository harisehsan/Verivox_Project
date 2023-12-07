@address_check
Feature: verify cities and street for given postcode

  @cities_check_for_postcode
  Scenario: Find the cities for a given postcode
  Given the address checking service endpoint: <"https://service.verivox.de/geo/latestv2/cities/">
  When I request the cities for postcode
    |10409|77716|
  Then I verify that status code is <200>
  And I should receive a response with only cities:
    |Berlin|Fischerbach|Haslach|Hofstetten|
  When I request the cities for postcode
    |22333|
  Then I verify that status code is <404>
  And I verify that response body is Empty


  @streets_check_for_postcode
  Scenario: Find the streets for a given postcode
    Given the address checking service endpoint: <"https://service.verivox.de/geo/latestv2/cities/">
    When I get the cities:
      |Berlin|Fischerbach|Haslach|Hofstetten|
    And postal code:
      |10409|77716|
    And I request the street for cities and post codes
    Then I verify that status code is <200>
    And I should receive a response with specified number of streets and verify Special German Characters:
    |29|34|123|40|