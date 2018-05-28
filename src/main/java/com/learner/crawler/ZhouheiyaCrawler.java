package com.learner.crawler;

import com.learner.spider.ParserObserver;
import com.learner.spider.RespContext;
import com.learner.spider.Spider;
import com.learner.spider.SpiderManager;
import com.learner.util.ContextUtil;

/**
 * Created by liufangliang on 2018/5/19.
 */
public class ZhouheiyaCrawler extends AbstractCrawler {

    public ZhouheiyaCrawler(Spider spider){
        this.setSpider(spider);
    }

    public void init(){
        getUrl("https://www.zhouheiya.cn/index.php/index-show-tid-5.html?id=0-7-0-8", null, new ParserObserver() {
            @Override
            public void afterRequest(RespContext response) {
                log.info(ContextUtil.getString(response));
            }
        });
    }

    public static void main(String[] args) {
        Spider spider= SpiderManager.instance().create();
        ZhouheiyaCrawler crawler=new ZhouheiyaCrawler(spider);
        crawler.init();
        spider.start();

    }
}
