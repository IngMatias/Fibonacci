package com.example.controllers;

import com.example.entities.FibonacciEntity;
import com.example.entities.FibonacciStatisticEntity;
import com.example.exceptions.FibonacciEntityNotFoundCustomException;
import com.example.exceptions.FibonacciNMustBeAPositiveIntegerCustomException;
import com.example.exceptions.FibonacciStatisticEntityNotFoundCustomException;
import com.example.services.FibonacciService;
import com.example.services.FibonacciStatisticService;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;
import java.util.stream.Stream;

@Path("/fibonacci")
public class FibonacciController {

    @Inject
    FibonacciService fibonacciService;

    @Inject
    FibonacciStatisticService fibonacciStatisticService;

    @PostConstruct
    public void init () {
        // fibo(0) = 0
        // fibo(1) = 1
        fibonacciService.save(new FibonacciEntity(0L, 0L));
        fibonacciService.save(new FibonacciEntity(1L, 1L));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FibonacciEntity> getAllFibonacci() {
        return fibonacciService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{n}")
    public FibonacciEntity getFibonacciByN(@RestPath Long n) throws FibonacciNMustBeAPositiveIntegerCustomException {
        if(n < 0) {
            throw new FibonacciNMustBeAPositiveIntegerCustomException();
        }

        List<FibonacciEntity> lastTwo = fibonacciService.findLastTwoLessThan(n);

        FibonacciEntity newFibonacci = fibonacciService.getFiboNFrom(
                lastTwo.get(0),
                lastTwo.get(1),
                n
        );

        FibonacciStatisticEntity fibonacciStatisticEntity = fibonacciStatisticService
                .findByFibonacciEntity(newFibonacci)
                .orElse(new FibonacciStatisticEntity(newFibonacci));

        fibonacciStatisticService.incrementQueryCounter(fibonacciStatisticEntity);

        return newFibonacci;
    }

}
