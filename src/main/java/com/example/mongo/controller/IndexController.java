package com.example.mongo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {

        return "father";

    }

    @RequestMapping(value = "/son", method = RequestMethod.GET)
    public String son() {

        return "son";

    }

    @RequestMapping(value = "/grandson", method = RequestMethod.GET)
    public String grandson() {

        return "grandson";

    }


}
