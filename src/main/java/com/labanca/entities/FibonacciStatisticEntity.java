package com.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "FIBONACCI_STATISTICS")
public class FibonacciStatisticEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FIBONACCI_STATISTIC_ID")
    long id;

    @OneToOne
    @JoinColumn(name = "FIBONACCI_ID", referencedColumnName = "FIBONACCI_ID", nullable = false)
    FibonacciEntity fibonacciEntity;

    @Column(name = "QUERY_COUNTER", nullable = false)
    long queryCounter;

    public FibonacciStatisticEntity() {}

    public FibonacciStatisticEntity(FibonacciEntity fibonacciEntity) {
        this.fibonacciEntity = fibonacciEntity;
        queryCounter = 0;
    }

    public long getId() {
        return id;
    }

    public FibonacciEntity getFibonacciEntity() {
        return fibonacciEntity;
    }

    public long getQueryCounter() {
        return queryCounter;
    }

    public void incrementQueryCounter() {
        queryCounter++;
    }

}
