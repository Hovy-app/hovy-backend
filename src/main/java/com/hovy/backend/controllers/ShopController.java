package com.hovy.backend.controllers;

import com.hovy.backend.services.MobileIdService;
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

    @GetMapping(value = "/scan/{id}/{phone}/{qr}")
    public @ResponseBody String scan(
            @PathVariable String id,
            @PathVariable String phone,
            @PathVariable String qr
    ) {
        return mobileIdService.challenge(phone, id);
    }
}
