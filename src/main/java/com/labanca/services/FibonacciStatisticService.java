package com.example.services;

import com.example.entities.FibonacciEntity;
import com.example.entities.FibonacciStatisticEntity;
import com.example.repositories.FibonacciStatisticRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FibonacciStatisticService {

    @Inject
    FibonacciStatisticRepository fibonacciStatisticRepository;

    public List<FibonacciStatisticEntity> findAll() {
        return fibonacciStatisticRepository.findAll();
    }

    public Optional<FibonacciStatisticEntity> findByFibonacciEntity(FibonacciEntity fibonacciEntity) {
        return fibonacciStatisticRepository.findByFibonacciEntity(fibonacciEntity);
    }

    public FibonacciStatisticEntity save(FibonacciStatisticEntity fibonacciStatisticEntity) {
        return fibonacciStatisticRepository.save(fibonacciStatisticEntity);
    }

    public void incrementQueryCounter(FibonacciStatisticEntity fibonacciStatisticEntity) {
        fibonacciStatisticEntity.incrementQueryCounter();
        save(fibonacciStatisticEntity);
    }

}
