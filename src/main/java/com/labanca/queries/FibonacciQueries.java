package com.example.queries;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FibonacciQueries {
    public String findLastTwoLessThan() {
        return "SELECT f " +
                "FROM FibonacciEntity f " +
                "WHERE f.n<=:n " +
                "ORDER BY f.n DESC ";
    }
}
