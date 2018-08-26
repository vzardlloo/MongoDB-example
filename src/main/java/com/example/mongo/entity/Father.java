package com.example.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Father {

    @Id
    String fatherId;

    String fatherName;


}
