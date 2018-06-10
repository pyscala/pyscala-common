package com.learner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liufangliang on 2018/6/10.
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    @ResponseBody
    public Map<String,String> test(){
        Map<String,String> map=new HashMap<String, String>();
        map.put("status","success");
        return map;
    }
}
