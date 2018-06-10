package com.learner.controller;
import com.learner.crawler.MaimaiCrawler;
import com.learner.entity.model.MaimaiUser;
import com.learner.service.pojoService.MaimaiService;
import com.learner.spider.Spider;
import com.learner.spider.SpiderManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Created by liufangliang on 2018/3/21.
 */
@Slf4j
@Controller
public class MaimaiController {
    @Autowired
    private MaimaiService service;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/maimai")
    public String  maimai(){
        Spider spider= SpiderManager.instance().create();
        MaimaiCrawler crawler= new MaimaiCrawler(spider);
        crawler.getMaimai();
        spider.start();
        MaimaiUser user=crawler.getUser();
        service.saveMaimaiUser(user);
        return "maimai.jsp";
    }

    @RequestMapping("/redisTest")
    public String  redisTest(){

//        Jedis  jedis=new Jedis("127.0.0.1",6379,100);
//        jedis.set("tess","tess");
//        redisService.add("test","test");
        redisTemplate.opsForValue().set("tets","stt");
        return "maimai.jsp";
    }

}
