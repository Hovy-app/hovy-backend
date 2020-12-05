package com.hovy.backend.dtos;

import com.hovy.backend.db.entities.Feedback;
import lombok.Data;

@Data
public class FeedbackDto {

    private long shopId;

    private Short rate;

    private String comment;

    private Feedback.ReasonType reasonType;

}
