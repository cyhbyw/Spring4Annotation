package com.cyh.aop;

public class MathCalculator {

    public int div(int i, int j) {
        System.out.println("目标方法 MathCalculator...div... i=" + i + ", j=" + j);
        return i / j;
    }
}
