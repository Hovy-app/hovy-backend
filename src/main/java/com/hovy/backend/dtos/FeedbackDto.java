package com.hovy.backend.dtos;

import lombok.Data;

@Data
public class FeedbackDto {

    private long shopId;

    private Short rate;

    private String comment;

}
