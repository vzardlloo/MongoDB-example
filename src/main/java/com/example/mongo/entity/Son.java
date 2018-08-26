package com.example.mongo.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "son")
public class Son {

    @Id
    String sonId;

    String fatherId;

    String sonName;

}
