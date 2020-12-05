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

        return String.format(
                "{" +
                        "\"queueNumber\": %d," +
                        " \"current\": %d," +
                        " \"peopleLeft\": %d" +
                "}",
                queueNumber,
                current,
                (20 - current + queueNumber) % 20
        );
    }

    @GetMapping(value = "/queueStatus/{shopId}/{serviceId}/{myNumber}")
    public @ResponseBody String queueStatus(
            @PathVariable long shopId,
            @PathVariable long serviceId,
            @PathVariable int myNumber
    ) {
        int current = shopService.getCurrent();

        return String.format(
                "{" +
                        "\"queueNumber\": %d," +
                        " \"current\": %d," +
                        " \"peopleLeft\": %d" +
                        "}",
                myNumber,
                current,
                (20 - current + myNumber) % 20
        );
    }

    @GetMapping(value = "/next/{shopId}/{serviceId}/{myNumber}")
    public @ResponseBody String next(
            @PathVariable long shopId,
            @PathVariable long serviceId,
            @PathVariable int myNumber
    ) {
        int current = shopService.getNext();

        return String.format(
                "{" +
                        "\"queueNumber\": %d," +
                        " \"current\": %d," +
                        " \"peopleLeft\": %d" +
                        "}",
                myNumber,
                current,
                (20 - current + myNumber) % 20
        );
    }

    @GetMapping(value = "/shopInfo/{qr}")
    public @ResponseBody String shopInfo(
            @PathVariable String qr
    ){
        Shop shop = shopService.getShop(qr);

        return mobileIdService.getServices(shop);
    }
}
