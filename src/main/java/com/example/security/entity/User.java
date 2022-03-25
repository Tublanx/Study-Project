package com.example.security.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_no")
    private Long no;

    private String id;
    private String pw;
    private String role;
    private boolean enabled;

}
