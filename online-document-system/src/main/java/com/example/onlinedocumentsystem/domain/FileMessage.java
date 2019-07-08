package com.example.onlinedocumentsystem.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class FileMessage {
    @Id
    private Long id;
    private String fileName;
    private double size;
    private Date uploadTime;
    private String uploadPerson;
}
