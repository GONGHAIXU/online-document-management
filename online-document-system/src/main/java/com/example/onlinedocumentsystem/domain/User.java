package com.example.onlinedocumentsystem.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private String profilePhoto;
    private String phoneNumber;
    private String mail;
    private Integer maximumCapacity;
    private Double residualCapacity;
    private int limitsOfAuthority;
    private int activationCode;
    public User(String mail) {
        this.mail = mail;
    }
    public User(){}
}
