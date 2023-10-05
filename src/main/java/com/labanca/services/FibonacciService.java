package com.example.services;

import com.example.entities.FibonacciEntity;
import com.example.repositories.FibonacciRepository;
import com.example.repositories.FibonacciRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class FibonacciService {

    @Inject
    FibonacciRepository fibonacciRepository;
    @Inject
    FibonacciRepositoryImpl fibonacciRepositoryImpl;

    public List<FibonacciEntity> findAll() {
        return fibonacciRepository.findAll();
    }

    public List<FibonacciEntity> findLastTwoLessThan(Long n) {
        return fibonacciRepositoryImpl.findLastTwoLessThan(n);
    }

    public Optional<FibonacciEntity> findByN(Long n) {
        return fibonacciRepository.findByN(n);
    }

    public void save(FibonacciEntity fibonacciEntity) {
        fibonacciRepository.save(fibonacciEntity);
    }

    public FibonacciEntity getFiboNFrom(
            FibonacciEntity nLessTwoFibonacci,
            FibonacciEntity nLessOneFibonacci,
            long n
    ) {

        System.out.println("START");
        System.out.println("fibo(n-2): " + nLessTwoFibonacci);
        System.out.println("fibo(n-1): " + nLessOneFibonacci);

        long fiboNLessTwo = nLessTwoFibonacci.getFiboN();

        FibonacciEntity newFibonacci = new FibonacciEntity(
                nLessOneFibonacci.getId(),
                nLessOneFibonacci.getN(),
                nLessOneFibonacci.getFiboN()
        );

        while(newFibonacci.getN() < n) {

            long aux = fiboNLessTwo;

            fiboNLessTwo = newFibonacci.getFiboN();
            newFibonacci = new FibonacciEntity(newFibonacci.getN() + 1, newFibonacci.getFiboN() + aux);
            this.save(newFibonacci);

            System.out.println("New Fibo: : " + newFibonacci);
        }

        System.out.println("END");

        return newFibonacci;
    }

}
