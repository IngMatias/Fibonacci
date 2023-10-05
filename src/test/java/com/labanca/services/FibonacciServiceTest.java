package com.example.services;

import com.example.entities.FibonacciEntity;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
public class FibonacciServiceTest {

    @Inject
    FibonacciService fibonacciService;

    @Test
    @TestTransaction
    public void fromBaseCalculation() {

        FibonacciEntity nLessTwoFibonacci = new FibonacciEntity(0L, 0L);
        FibonacciEntity nLessOneFibonacci = new FibonacciEntity(1L, 1L);
        long n = 20;

        FibonacciEntity calculatedFibonacciEntity = fibonacciService.getFiboNFrom(
                nLessTwoFibonacci,
                nLessOneFibonacci,
                n
        );

        assert(calculatedFibonacciEntity.getN() == 20);
        assert(calculatedFibonacciEntity.getFiboN() == 6765);
    }

    @Test
    @TestTransaction
    public void fromCacheCalculation() {

        FibonacciEntity nLessTwoFibonacci = new FibonacciEntity(11L, 89L);
        FibonacciEntity nLessOneFibonacci = new FibonacciEntity(12L, 144L);
        long n = 20;

        FibonacciEntity calculatedFibonacciEntity = fibonacciService.getFiboNFrom(
                nLessTwoFibonacci,
                nLessOneFibonacci,
                n
        );

        assert(calculatedFibonacciEntity.getN() == 20);
        assert(calculatedFibonacciEntity.getFiboN() == 6765);
    }

    @Test
    @TestTransaction
    public void findLastTwoLessThanTest() {

        FibonacciEntity nLessTwoFibonacci = new FibonacciEntity(0L, 0L);
        FibonacciEntity nLessOneFibonacci = new FibonacciEntity(1L, 1L);
        long n = 30;

        fibonacciService.getFiboNFrom(
                nLessTwoFibonacci,
                nLessOneFibonacci,
                n
        );

        List<FibonacciEntity> fibonacciEntityLastTwoLessThanTwentyFive = fibonacciService.findLastTwoLessThan(25L);
        FibonacciEntity twentyFourFibonacciEntity = fibonacciEntityLastTwoLessThanTwentyFive.get(0);
        FibonacciEntity twentyFiveFibonacciEntity = fibonacciEntityLastTwoLessThanTwentyFive.get(1);

        assert(twentyFourFibonacciEntity.getN() == 24);
        assert(twentyFourFibonacciEntity.getFiboN() == 46368);

        assert(twentyFiveFibonacciEntity.getN() == 25);
        assert(twentyFiveFibonacciEntity.getFiboN() == 75025);
    }
}
