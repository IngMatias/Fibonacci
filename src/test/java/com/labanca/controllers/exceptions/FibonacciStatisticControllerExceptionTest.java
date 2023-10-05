package com.example.controllers.exceptions;

import com.example.controllers.FibonacciController;
import com.example.controllers.FibonacciStatisticController;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class FibonacciStatisticControllerExceptionTest {

    @TestHTTPEndpoint(FibonacciStatisticController.class)
    @TestHTTPResource("-1")
    URL fibonacciStatisticMinusOne;

    @TestHTTPEndpoint(FibonacciStatisticController.class)
    @TestHTTPResource("5")
    URL fibonacciStatisticFive;

    @TestHTTPEndpoint(FibonacciStatisticController.class)
    @TestHTTPResource("7")
    URL fibonacciStatisticSeven;

    @Test
    @TestTransaction
    public void FibonacciNMustBeAPositiveIntegerCustomExceptionTest() {
        given()
                .when().get(fibonacciStatisticMinusOne)
                .then()
                .statusCode(500)
                .body(containsString("FibonacciNMustBeAPositiveIntegerCustomException"));
    }

    @Test
    @TestTransaction
    public void FibonacciEntityNotFoundCustomExceptionTest() {
        given()
                .when().get(fibonacciStatisticFive)
                .then()
                .statusCode(500)
                .body(containsString("FibonacciEntityNotFoundCustomException"));
    }

    @Test
    @TestTransaction
    public void FibonacciStatisticEntityNotFoundCustomExceptionTest() {
        given()
                .when().get(fibonacciStatisticSeven)
                .then()
                .statusCode(500)
                .body(containsString("FibonacciStatisticEntityNotFoundCustomException"));
    }

}
