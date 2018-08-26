package com.example.mongo.repository;


import com.example.mongo.entity.GrandSon;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface GrandSonRepository extends MongoRepository<GrandSon, String> {


}
