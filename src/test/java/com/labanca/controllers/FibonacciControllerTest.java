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
public class FibonacciControllerTest {

    @TestHTTPEndpoint(FibonacciController.class)
    @TestHTTPResource("10")
    URL fibonacciTen;

    @TestHTTPEndpoint(FibonacciController.class)
    @TestHTTPResource("15")
    URL fibonacciFifteen;

    @TestHTTPEndpoint(FibonacciController.class)
    @TestHTTPResource
    URL fibonacciFindAll;

    @Test
    @TestTransaction
    public void fiboTenTest() {
        given()
                .when().get(fibonacciTen)
                .then()
                .statusCode(200)
                .body(
                        "n", is(10),
                        "fiboN", is(55)
                );
    }

    @Test
    @TestTransaction
    public void fiboFifteenTest() {
        given()
                .when().get(fibonacciFifteen)
                .then()
                .statusCode(200)
                .body(
                        "n", is(15),
                        "fiboN", is(610)
                );
    }

    @Test
    @TestTransaction
    public void findAllTest() {
        given()
                .when().get(fibonacciFindAll)
                .then()
                .statusCode(200)
                .body(
                        "size()", is(9)
                );
    }

}
