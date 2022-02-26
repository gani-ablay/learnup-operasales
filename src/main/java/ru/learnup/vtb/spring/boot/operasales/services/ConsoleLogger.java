package ru.learnup.vtb.spring.boot.operasales.services;


import ru.learnup.vtb.spring.boot.operasales.services.interfaces.Logger;

public class ConsoleLogger implements Logger {

    private final String prefix;

    public ConsoleLogger(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(Object obj) {
        System.out.println(prefix + ": " + obj);
    }
}
