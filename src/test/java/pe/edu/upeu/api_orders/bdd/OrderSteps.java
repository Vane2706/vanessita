package pe.edu.upeu.api_orders.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import pe.edu.upeu.api_orders.model.Order;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderSteps {

    private ResponseEntity<Order> postResponse;
    private ResponseEntity<List> getResponse;

    private RestTemplate restTemplate = new RestTemplate();

    private final String BASE_URL = "http://localhost:1711/api/orders";

    @Given("the order API is running")
    public void the_order_api_is_running() {

    }

    @When("I send a POST request with customer {string} and amount {double}")
    public void i_send_a_post_request(String customer, Double amount) {

        Order order = new Order(null, customer, amount);

        postResponse = restTemplate.postForEntity(
                BASE_URL,
                order,
                Order.class
        );
    }

    @Then("the POST response status should be {int}")
    public void the_post_response_status_should_be(Integer statusCode) {

        assertEquals(statusCode, postResponse.getStatusCode().value());

        assertNotNull(postResponse.getBody());
    }

    @When("I send a GET request to orders")
    public void i_send_a_get_request_to_orders() {

        getResponse = restTemplate.getForEntity(
                BASE_URL,
                List.class
        );
    }

    @Then("the GET response status should be {int}")
    public void the_get_response_status_should_be(Integer statusCode) {

        assertEquals(statusCode, getResponse.getStatusCode().value());
    }
}