package org.kuzstu;

public class Calculator {
    public int sum(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public double divide(int a, int b) {
        double result;
        if (b == 0) {
            throw new IllegalArgumentException("Нельзя делить на ноль");
        } else {
            result = Double.valueOf(a) / Double.valueOf(b);
        }
        return result;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}