package ru.learnup.vtb.spring.boot.operasales.services;

import ru.learnup.vtb.spring.boot.operasales.services.interfaces.Logger;

public class EmailLogger implements Logger {
    @Override
    public void print(Object obj) {
        System.out.println("Email: " + obj);
    }
}
