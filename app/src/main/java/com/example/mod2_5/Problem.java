package com.example.mod2_5;

import java.util.Random;

public class Problem {

    private int result;
    private final Random random = new Random();

    int getRandom(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    public float getResult() {
        return result;
    }
    public float getNotResult() {
        return result + getRandom(-9, 9);
    }

    public String getProblem() {
        int a = getRandom(-50, 50);
        int b = getRandom( - 50, 50);
        String sign;
        switch (getRandom(0, 2)){
            case 1:
                sign = getRandomSign_sqr();
                break;
            case 0:
                sign = getRandomSign();
                break;
            default:
                return random.nextBoolean() ? getRandomSign() : getRandomSign_sqr();
        }
        if (sign.equals("+")) result = a + b;
        else if (sign.equals("-")) result = a - b;
        else if (sign.equals("/")) result = a / b;
        else if (sign.equals("*")) result = a * b;
        return a + " "+sign +" "+ b + " =";
    }
    private String getRandomSign() {
        return random.nextBoolean() ? "-" : "+";
    }
    private String getRandomSign_sqr() {
        return random.nextBoolean()? "/" : "*";

    }
}
