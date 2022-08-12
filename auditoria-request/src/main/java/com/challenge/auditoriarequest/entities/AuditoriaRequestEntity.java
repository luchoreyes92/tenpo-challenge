package com.challenge.auditoriarequest.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "auditoria_requests")
public class AuditoriaRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "uri")
    private String uri;
    @Column(name = "status")
    private Integer status;
    @Column(name = "response")
    private String response;
    @Column(name = "username")
    private String username;

}
