package com.hovy.backend.services;

import com.hovy.backend.db.entities.Shop;
import com.hovy.backend.db.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    private int current = 1;

    @Autowired
    private ShopRepository shopRepository;

    public Shop getShop(String qr) {
        switch (qr) {
            case "aaa":
                return shopRepository.getOne(1L);
            case "bbb":
                return shopRepository.getOne(2L);
            case "ccc":
                return shopRepository.getOne(3L);
            default:
                throw new IllegalArgumentException("Shop not found");
        }
    }

    public int enterQueue() {
        return (int) Math.ceil(Math.random() * 20);
    }

    public int getCurrent() {
        return current;
    }

    public int getNext() {
        current ++;

        if (current > 20) {
            current = 1;
        }

        return current;
    }

    public String[] getServices(long shopId) {
        if (shopId == 1) {
            return new String[] {"Collect parcel", "Financial services", "Send parcel"};
        }

        if (shopId == 2) {
            return new String[] {"Advice on purchase", "Collect product"};
        }

        if (shopId == 3) {
            return new String[] {"Collect item", "Return item"};
        }

        throw new IllegalArgumentException("Shop not found");
    }
}
