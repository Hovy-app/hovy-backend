package com.hovy.backend.controllers;

import com.hovy.backend.dtos.FeedbackDto;
import com.hovy.backend.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/feedback")
    public @ResponseBody String giveFeeback(
            @RequestBody FeedbackDto feedback
    ) {
        try {
            feedbackService.addFeedback(feedback.getShopId(), feedback.getRate(), feedback.getComment(), feedback.getReasonType());
        } catch (Exception e) {
            return String.format("{\"status\": \"Error\", \"message\": \"%s\"}", e.getLocalizedMessage());
        }

        return "{\"status\": \"OK\"}";
    }
}
