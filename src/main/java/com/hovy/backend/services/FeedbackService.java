package com.hovy.backend.services;

import com.hovy.backend.db.entities.Feedback;
import com.hovy.backend.db.repositories.FeedbackRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public void addFeedback(long shopId, Short rate, String comment) {
        if (rate == null && StringUtils.isBlank(comment)) {
            return;
        }

        feedbackRepository.save(
                Feedback.builder()
                        .shopId(shopId)
                        .rate(rate)
                        .comment(comment)
                        .build()
        );
    }
}
