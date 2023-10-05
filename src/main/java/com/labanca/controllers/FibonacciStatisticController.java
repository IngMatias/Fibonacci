package com.example.controllers;

import com.example.entities.FibonacciEntity;
import com.example.entities.FibonacciStatisticEntity;
import com.example.exceptions.FibonacciEntityNotFoundCustomException;
import com.example.exceptions.FibonacciNMustBeAPositiveIntegerCustomException;
import com.example.exceptions.FibonacciStatisticEntityNotFoundCustomException;
import com.example.services.FibonacciService;
import com.example.services.FibonacciStatisticService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

@Path("/fibonacciStatistic")
public class FibonacciStatisticController {

    @Inject
    FibonacciService fibonacciService;

    @Inject
    FibonacciStatisticService fibonacciStatisticService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FibonacciStatisticEntity> getAllFibonacciStatistics() {
        return fibonacciStatisticService.findAll();
    }

    @Path("/{n}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public FibonacciStatisticEntity getFibonacciStatisticsByN(@RestPath Long n) throws FibonacciStatisticEntityNotFoundCustomException, FibonacciNMustBeAPositiveIntegerCustomException, FibonacciEntityNotFoundCustomException {
        if (n < 0) {
            throw new FibonacciNMustBeAPositiveIntegerCustomException();
        }
        FibonacciEntity fibonacciEntity = fibonacciService.findByN(n)
                .orElseThrow(FibonacciEntityNotFoundCustomException::new);

        return fibonacciStatisticService.findByFibonacciEntity(fibonacciEntity)
                .orElseThrow(FibonacciStatisticEntityNotFoundCustomException::new);
    }

}
