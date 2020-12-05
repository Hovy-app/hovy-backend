package com.hovy.backend.db.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shops")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "template")
    private String template;

    @Column(name = "publicKey")
    private String publicKey;

    @Column(name = "address")
    private String address;

    @Column(name = "logo_url")
    private String logoUrl;

}
