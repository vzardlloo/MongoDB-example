package com.example.mongo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.mongo.entity.Father;
import com.example.mongo.entity.GrandSon;
import com.example.mongo.entity.Son;
import com.example.mongo.repository.GrandSonRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@RestController
@Api(tags = "节点的操作")
public class NodeController {

    @Autowired
    GrandSonRepository grandSonRepository;


    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(value = "/grandson", method = RequestMethod.POST)
    @ApiOperation("增加一个孙节点")
    public void addGrandSon(@RequestBody GrandSon grandson) {

        mongoTemplate.save(grandson, "grandson");

    }


    @RequestMapping(value = "/son", method = RequestMethod.POST)
    @ApiOperation("增加一个子节点")
    public void addSon(@RequestBody Son son) {

        mongoTemplate.save(son, "son");

    }


    @RequestMapping(value = "/father", method = RequestMethod.POST)
    @ApiOperation("增加一个父节点")
    public void addFather(@RequestBody Father father) {

        mongoTemplate.save(father, "father");

    }


    @RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
    public Object getAllNodeByFather(@PathVariable("id") String fatherId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Query query = new Query();
        Criteria criteria1 = Criteria.where("fatherId").is(fatherId);
        query.addCriteria(criteria1);
        List<Son> sons = mongoTemplate.find(query, Son.class);
        hashMap.put("son", sons);
        List grandsons = new LinkedList();
        for (int i = 0; i < sons.size(); i++) {
            String id = sons.get(i).getSonId();
            // System.out.println(id);
            Query query1 = new Query();
            Criteria criteria = Criteria.where("fatherId").is(id);
            query1.addCriteria(criteria);
            List<GrandSon> grandSons = mongoTemplate.find(query1, GrandSon.class);
            //System.out.println(grandSons);
            grandsons.addAll(grandSons);

        }

        hashMap.put("grandson", grandsons);
        return JSONObject.toJSON(hashMap);
    }


    @RequestMapping(value = "/grandson/{id}", method = RequestMethod.GET)
    public List<GrandSon> getGrandSonByFatherId(@PathVariable("id") String id) {
        Query query = new Query();
        Criteria criteria = Criteria.where("fatherId").is(id);
        query.addCriteria(criteria);

        return mongoTemplate.find(query, GrandSon.class);
    }

    @RequestMapping(value = "/grandsons", method = RequestMethod.GET)
    public List<GrandSon> getAllGrandSon() {

        return mongoTemplate.findAll(GrandSon.class);
    }


}
