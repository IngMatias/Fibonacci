package com.example.controllers.exceptions;

import com.example.controllers.FibonacciController;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class FibonacciControllerExceptionTest {

    @TestHTTPEndpoint(FibonacciController.class)
    @TestHTTPResource("-1")
    URL fibonacciMinusOne;

    @Test
    @TestTransaction
    public void FibonacciNMustBeAPositiveIntegerCustomExceptionTest() {
        given()
                .when().get(fibonacciMinusOne)
                .then()
                .statusCode(500)
                .body(containsString("FibonacciNMustBeAPositiveIntegerCustomException"));
    }

}
