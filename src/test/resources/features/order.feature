Feature: Order API

  Scenario: Create a new order
    Given the order API is running
    When I send a POST request with customer "Vanessa" and amount 1500.0
    Then the POST response status should be 201

  Scenario: Get all orders
    Given the order API is running
    When I send a GET request to orders
    Then the GET response status should be 200