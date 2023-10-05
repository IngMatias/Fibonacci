package com.example.services;

import com.example.entities.FibonacciEntity;
import com.example.entities.FibonacciStatisticEntity;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class FibonacciStatisticServiceTest {

    @Inject
    FibonacciService fibonacciService;

    @Inject
    FibonacciStatisticService fibonacciStatisticService;

    @Test
    @TestTransaction
    public void simpleTest() {

        FibonacciEntity fibonacciEntity = new FibonacciEntity(2L, 1L);
        fibonacciService.save(fibonacciEntity);

        FibonacciStatisticEntity fibonacciStatisticEntity = new FibonacciStatisticEntity(fibonacciEntity);
        fibonacciStatisticService.save(fibonacciStatisticEntity);

        assert(fibonacciStatisticService.findByFibonacciEntity(fibonacciEntity).isPresent());
        assert(fibonacciStatisticService.findByFibonacciEntity(fibonacciEntity).get().getFibonacciEntity().getN() == 2);
        assert(fibonacciStatisticService.findByFibonacciEntity(fibonacciEntity).get().getFibonacciEntity().getFiboN() == 1);
    }

}
