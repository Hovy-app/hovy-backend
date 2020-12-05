package com.hovy.backend.controllers;

import com.hovy.backend.services.MobileIdService;
import com.hovy.backend.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        long shopId = shopService.getShopId(qr);

        return mobileIdService.challenge(phone, id, shopId);
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
}
