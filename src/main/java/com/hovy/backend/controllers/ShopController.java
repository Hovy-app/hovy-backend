package com.hovy.backend.controllers;

import com.hovy.backend.db.entities.Shop;
import com.hovy.backend.services.MobileIdService;
import com.hovy.backend.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {

    @Autowired
    private MobileIdService mobileIdService;

    @Autowired
    private ShopService shopService;

    @GetMapping(value = "/scan/{id}/{phone}/{qr}")
    public @ResponseBody String scan(
            @PathVariable String id,
            @PathVariable String phone,
            @PathVariable String qr
    ) {
        Shop shop = shopService.getShop(qr);

        return mobileIdService.challenge(phone, id, shop);
    }

    @GetMapping(value = "/enterQueue/{shopId}/{serviceId}")
    public @ResponseBody String enterQueue(
            @PathVariable long shopId,
            @PathVariable long serviceId
    ) {
        int queueNumber = shopService.enterQueue();
        int current = shopService.getCurrent();

        return String.format("{\"queueNumber\": %d, \"current\": %d}", queueNumber, current);
    }

    @GetMapping(value = "/next/{shopId}/{serviceId}")
    public @ResponseBody String next(
            @PathVariable long shopId,
            @PathVariable long serviceId
    ) {
        int current = shopService.getNext();

        return String.format("{\"current\": %d}", current);
    }

    @GetMapping(value = "/shopInfo/{qr}")
    public @ResponseBody String shopInfo(
            @PathVariable String qr
    ){
        Shop shop = shopService.getShop(qr);

        return mobileIdService.getServices(shop);
    }
}
