package com.mycompany.imooc.cr.test;

public class Calculator {

    public int plus(int numberOne, int numberTwo) {
        int result = numberOne + numberTwo;
        if (numberOne > 0 && numberTwo > 0) {
            if (result < Math.max(numberOne, numberTwo)) {
                throw new IllegalArgumentException("calculate overflow");
            }
        }
        return result;
    }

    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("divisor should not be zero");
        }
        return dividend / divisor;
    }
}
