package com.example;

public class FooService {

    public String methodThatCallsNewFooInstanceMethod() {
        return new Foo().stringReturningInstanceMethod();
    }
}
