package com.example.repositories;

import com.example.entities.FibonacciEntity;
import com.example.entities.FibonacciStatisticEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface FibonacciStatisticRepository extends Repository<FibonacciStatisticEntity, Long> {
    List<FibonacciStatisticEntity> findAll();
    Optional<FibonacciStatisticEntity> findByFibonacciEntity(FibonacciEntity fibonacciEntity);
    FibonacciStatisticEntity save(FibonacciStatisticEntity fibonacciStatisticEntity);
}
