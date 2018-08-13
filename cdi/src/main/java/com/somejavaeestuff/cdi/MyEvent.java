package com.somejavaeestuff.cdi;

public class MyEvent {

    private final String value;

    public MyEvent(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
