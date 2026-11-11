package com.example.review;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    void addsTwoNumbers(){
        assertEquals(5, new Calculator().add(2,3));
    }
}
