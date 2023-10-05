package com.example.controllers.mocks;

import com.example.entities.FibonacciEntity;
import com.example.entities.FibonacciStatisticEntity;
import com.example.services.FibonacciStatisticService;
import io.quarkus.test.Mock;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.lang3.NotImplementedException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Mock
@ApplicationScoped
public class FibonacciStatisticServiceMock extends FibonacciStatisticService {

    @Override
    public List<FibonacciStatisticEntity> findAll() {
        return new LinkedList<>() {{
            add(new FibonacciStatisticEntity());
            add(new FibonacciStatisticEntity());
            add(new FibonacciStatisticEntity());
            add(new FibonacciStatisticEntity());
        }};
    }

    @Override
    public Optional<FibonacciStatisticEntity> findByFibonacciEntity(FibonacciEntity fibonacciEntity) {

        if (fibonacciEntity.getN() == 17) {
            FibonacciStatisticEntity fibonacciStatisticEntity = new FibonacciStatisticEntity(fibonacciEntity);
            fibonacciStatisticEntity.incrementQueryCounter();
            fibonacciStatisticEntity.incrementQueryCounter();
            return Optional.of(fibonacciStatisticEntity);
        }

        if (fibonacciEntity.getN() == 12) {
            FibonacciStatisticEntity fibonacciStatisticEntity = new FibonacciStatisticEntity(fibonacciEntity);
            fibonacciStatisticEntity.incrementQueryCounter();
            fibonacciStatisticEntity.incrementQueryCounter();
            fibonacciStatisticEntity.incrementQueryCounter();
            return Optional.of(fibonacciStatisticEntity);
        }

        if (fibonacciEntity.getN() == 7) {
            return Optional.empty();
        }

        if (fibonacciEntity.getN() == 10 || fibonacciEntity.getN() == 15) {
            return Optional.empty();
        }

        if (fibonacciEntity.getN() == 2) {
            return super.findByFibonacciEntity(fibonacciEntity);
        }

        throw new NotImplementedException();
    }

    @Override
    public void incrementQueryCounter(FibonacciStatisticEntity fibonacciStatisticEntity) {
        fibonacciStatisticEntity.incrementQueryCounter();
    }

}
