package com.csipon.planningpocker.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class IdGenerator {
    //    private AtomicInteger integer = new AtomicInteger(new Random().nextInt());
    private AtomicInteger integer = new AtomicInteger(1);


    public Integer generateID() {
        return integer.getAndIncrement();
    }
}
