package com.hovy.backend.services;

import org.springframework.stereotype.Service;

@Service
public class ShopService {

    private int current = 1;

    public long getShopId(String qr) {
        return 1L;
    }

    public int enterQueue() {
        return (int) Math.ceil(Math.random() * 20);
    }

    public int getCurrent() {
        return current;
    }

    public int next() {
        current ++;

        if (current > 20) {
            current = 1;
        }

        return current;
    }
}
