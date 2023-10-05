package com.example.repositories;

import com.example.entities.FibonacciEntity;
import com.example.queries.FibonacciQueries;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FibonacciRepositoryImpl {

    @Inject
    FibonacciQueries fibonacciQueries;

    @Autowired
    EntityManager entityManager;

    public List<FibonacciEntity> findLastTwoLessThan(Long n) {

        String query = fibonacciQueries.findLastTwoLessThan();

        List<FibonacciEntity> lastTwoLessThanN = entityManager.createQuery(query, FibonacciEntity.class)
            .setParameter("n", n)
            .setMaxResults(2)
            .getResultList();

        Collections.reverse(lastTwoLessThanN);

        return lastTwoLessThanN;
    }
}
