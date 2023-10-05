package com.example.controllers;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class FibonacciStatisticTest {

    @TestHTTPEndpoint(FibonacciStatisticController.class)
    @TestHTTPResource("12")
    URL fibonacciStatisticTwelve;

    @TestHTTPEndpoint(FibonacciStatisticController.class)
    @TestHTTPResource("17")
    URL fibonacciStatisticSeventeen;

    @TestHTTPEndpoint(FibonacciStatisticController.class)
    @TestHTTPResource
    URL fibonacciStatisticFindAll;

    @Test
    @TestTransaction
    public void fiboTwelveTest() {
        given()
                .when().get(fibonacciStatisticTwelve)
                .then()
                .statusCode(200)
                .body(
                        "fibonacciEntity.n", is(12),
                        "queryCounter", is(3)
                );
    }

    @Test
    @TestTransaction
    public void fiboFifteenTest() {
        given()
                .when().get(fibonacciStatisticSeventeen)
                .then()
                .statusCode(200)
                .body(
                        "fibonacciEntity.n", is(17),
                        "queryCounter", is(2)
                );
    }

    @Test
    @TestTransaction
    public void fibonacciFindAll() {
        given()
                .when().get(fibonacciStatisticFindAll)
                .then()
                .statusCode(200)
                .body(
                        "size()", is(4)
                );
    }
}
