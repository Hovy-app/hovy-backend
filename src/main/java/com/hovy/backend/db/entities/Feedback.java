package com.hovy.backend.db.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "feedbacks")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Feedback implements Serializable {

    public enum ReasonType {
        LONG_QUEUE,
        LONG_WAIT,
        WRONG_PLACE,
        PERSONAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "shop_id")
    private long shopId;

    @Column(name = "rate")
    private Short rate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "reason_type")
    @Enumerated(EnumType.ORDINAL)
    private ReasonType reasonType;

}
