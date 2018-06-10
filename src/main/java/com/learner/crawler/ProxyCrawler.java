package com.learner.crawler;

import com.learner.spider.ParserObserver;
import com.learner.spider.RespContext;
import com.learner.spider.Spider;
import com.learner.spider.SpiderManager;
import com.learner.util.ContextUtil;

/**
 * Created by liufangliang on 2018/6/3.
 */
public class ProxyCrawler extends AbstractCrawler{

    public ProxyCrawler(Spider spider){
        this.setSpider(spider);
    }
    /**
     * xici proxy
     */
    public void getProxyFromXici(){

        for (int i = 1; i < 4; i++) {
            getUrl("http://www.xicidaili.com/nn/"+i, null, new ParserObserver() {
                @Override
                public void afterRequest(RespContext response) {
                    String cont= ContextUtil.getString(response);

                }
            });
        }
    }

    public static void main(String[] args) {
        Spider spider= SpiderManager.instance().create();
        ProxyCrawler crawler=new ProxyCrawler(spider);
        crawler.getProxyFromXici();
        spider.start();
    }
}
