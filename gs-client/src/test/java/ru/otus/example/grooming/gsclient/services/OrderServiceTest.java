package ru.otus.example.grooming.gsclient.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void testCreateOrder() {
    }

    @Test
    void testDivision() {
        int a = 175 / 60;
        int b = 175 % 60;
        int c = 180 % 60;
        System.out.println("175 / 60 = " + a);
        System.out.println("175 % 60 = " + b);
        System.out.println("180 % 60 = " + c);
    }
}