package com.example.repositories;

import com.example.entities.FibonacciEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface FibonacciRepository extends Repository<FibonacciEntity, Long> {
    List<FibonacciEntity> findAll();
    Optional<FibonacciEntity> findByN(Long n);
    FibonacciEntity save(FibonacciEntity fibonacciEntity);

}
