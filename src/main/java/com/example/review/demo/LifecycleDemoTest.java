package com.example.review.demo;

import org.junit.jupiter.api.*;

public class LifecycleDemoTest {

    private int counter = 0;

    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("beforeEach, instance=" + System.identityHashCode(this));
    }

    @Test
    void TestA() {
        counter++;
        System.out.println("TestA counter=" + counter);
    }

    @Test
    void TestB() {
        counter++;
        System.out.println("TestB  counter=" + counter);
    }

    @AfterAll
    static void afterAll() {
        System.out.println("afterAll");
    }

    @AfterEach
    void afterEach() {
        System.out.println("afterEach");
    }

}
