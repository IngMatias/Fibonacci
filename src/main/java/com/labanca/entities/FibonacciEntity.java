package com.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "FIBONACCI")
public class FibonacciEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FIBONACCI_ID")
    private Long id;

    @Column(name="N", nullable = false, unique = true)
    private Long n;

    @Column(name="FIBO_N", nullable = false)
    private Long fiboN;

    public FibonacciEntity() {}

    public FibonacciEntity(Long n, Long fiboN) {
        this.n = n;
        this.fiboN = fiboN;
    }

    public FibonacciEntity(Long id, Long n, Long fiboN) {
        this.id = id;
        this.n = n;
        this.fiboN = fiboN;
    }

    public Long getId() {
        return id;
    }
    public Long getN() {
        return n;
    }
    public Long getFiboN() {
        return fiboN;
    }

    @Override
    public String toString() {
        return String.format("Id: %s, N: %s, FiboN: %s", id, n, fiboN);
    }

}