package com.learner.crawler;

import com.learner.spider.ParserObserver;
import com.learner.spider.RespContext;
import com.learner.spider.Spider;
import com.learner.spider.SpiderManager;
import com.learner.util.ContextUtil;

/**
 * Created by liufangliang on 2018/5/6.
 */
public class BTCCrawler extends AbstractCrawler {

    public BTCCrawler(Spider spider){
        this.setSpider(spider);
    }


    public void getDetail(){
        getUrl("https://hubblerating.com/project/getAll?from=groupmessage&isappinstalled=0", null, new ParserObserver() {
            public void afterRequest(RespContext response) {
                String html= ContextUtil.getString(response);
                log.info(html);

            }
        });
    }

    public static void main(String[] args) {
        Spider spider= SpiderManager.instance().create();
        BTCCrawler crawler=new BTCCrawler(spider);
        crawler.getDetail();
        spider.start();
    }
}
