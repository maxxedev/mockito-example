package com.example;

import java.util.Random;

public class DiceRoller {

    private final Random random = new Random();
    private int numSides;

    public DiceRoller() {
        this(6);
    }

    public DiceRoller(int numSides) {
        this.numSides = numSides;
    }

    public int roll() {
        return random.nextInt(numSides) + 1;
    }
}
