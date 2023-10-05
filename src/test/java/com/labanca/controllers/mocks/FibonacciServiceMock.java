package com.example.controllers.mocks;

import com.example.entities.FibonacciEntity;
import com.example.services.FibonacciService;
import io.quarkus.test.Mock;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.lang3.NotImplementedException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Mock
@ApplicationScoped
public class FibonacciServiceMock extends FibonacciService {

    @Override
    public List<FibonacciEntity> findAll() {
        return new LinkedList<FibonacciEntity>() {{
            add(new FibonacciEntity(2L, 1L));
            add(new FibonacciEntity(3L, 2L));
            add(new FibonacciEntity(4L, 3L));
            add(new FibonacciEntity(5L, 5L));
            add(new FibonacciEntity(6L, 8L));
            add(new FibonacciEntity(7L, 13L));
            add(new FibonacciEntity(8L, 21L));
            add(new FibonacciEntity(9L, 34L));
            add(new FibonacciEntity(10L, 55L));
        }};
    }

    @Override
    public List<FibonacciEntity> findLastTwoLessThan(Long n) {
        if (n == 10 || n == 15) {
            return new LinkedList<FibonacciEntity>() {{
                add(new FibonacciEntity(5L, 5L));
                add(new FibonacciEntity(6L, 8L));
            }};
        }
        if (n == 25) {
            return super.findLastTwoLessThan(n);
        }
        throw new NotImplementedException();
    }

    @Override
    public FibonacciEntity getFiboNFrom(
            FibonacciEntity nLessTwoFibonacci,
            FibonacciEntity nLessOneFibonacci,
            long n
    ) {
        if (
                nLessTwoFibonacci.getN() == 5 &&
                nLessOneFibonacci.getN() == 6 &&
                n == 10
        ) {
            return new FibonacciEntity(10L, 55L);
        }
        if (
                nLessTwoFibonacci.getN() == 5 &&
                nLessOneFibonacci.getN() == 6 &&
                n == 15
        ) {
            return new FibonacciEntity(15L, 610L);
        }

        if (n==11 || n==20 || n==30) {
            return super.getFiboNFrom(nLessTwoFibonacci, nLessOneFibonacci, n);
        }

        throw new NotImplementedException();

    }

    @Override
    public Optional<FibonacciEntity> findByN(Long n) {

        if (n == 17) {
            return Optional.of(new FibonacciEntity(17L, null));
        }
        if (n == 12) {
            return Optional.of(new FibonacciEntity(12L, null));
        }
        if (n == 7) {
            return Optional.of(new FibonacciEntity(7L, 8L));
        }
        if (n == 5) {
            return Optional.empty();
        }
        throw new NotImplementedException();

    }


}
