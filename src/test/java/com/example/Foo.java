package com.example;

public class Foo {
    public static String stringReturningStaticMethod() {
        return "originalRealValue";
    }

    public static int intReturningStaticMethod() {
        return 2;
    }

    public String stringReturningInstanceMethod() {
        return "originalRealValue";
    }

    public int intReturningInstanceMethod() {
        return 1;
    }
}