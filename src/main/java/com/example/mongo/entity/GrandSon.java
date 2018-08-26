package com.example.mongo.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "grandson")
public class GrandSon {


    @Id
    String grandsonId;

    String fatherId;

    String grandsonName;

}
