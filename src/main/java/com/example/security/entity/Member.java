package com.example.security.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue
    private Long no;

    private String id;
    private String password;
    private String name;
    private String email;

}
