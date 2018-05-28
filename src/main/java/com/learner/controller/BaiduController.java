package com.learner.controller;

import com.learner.crawler.BaiduCrawler;
import com.learner.spider.Spider;
import com.learner.spider.SpiderManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liufangliang on 2018/3/4.
 */
@Controller
public class BaiduController {

    @RequestMapping("/baidu")
    public void baidu(){

        Spider spider = SpiderManager.instance().create();
        BaiduCrawler crawler=new BaiduCrawler(spider);
        crawler.init();
        SpiderManager.instance().start(spider);


    }
}
