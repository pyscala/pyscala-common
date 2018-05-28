package com.learner.crawler;

import com.learner.entity.model.MaimaiUser;
import com.learner.spider.ParserObserver;
import com.learner.spider.RespContext;
import com.learner.spider.Spider;
import com.learner.spider.SpiderManager;
import com.learner.util.ContextUtil;
import lombok.Getter;

/**
 * Created by liufangliang on 2018/3/21.
 */
@Getter
public class MaimaiCrawler extends AbstractCrawler {

    public MaimaiCrawler(Spider spider) {
        super(spider);
    }

    private MaimaiUser user;

    public void getMaimai(){
        getUrl("https://maimai.cn/", null, new ParserObserver() {
            public void afterRequest(RespContext response) {
                getUrl("https://acc.maimai.cn/login", null, new ParserObserver() {
                    @Override
                    public void afterRequest(RespContext response) {
                        String [][] params=new String[][]{
                                {"m","15736873319"},
                                {"p","liuliang"},
                                {"to",""},
                                {"pa","+86"}
                        };
                        String [][] headers=new String[][]{
                                {"content-type","application/x-www-form-urlencoded"},
                                {"referer","https://acc.maimai.cn/login"},
                                {"origin","https://acc.maimai.cn"}
                        };
                        postUrl("https://acc.maimai.cn/login", params, null, headers, new ParserObserver() {
                            @Override
                            public void afterRequest(RespContext response) {
                                String cont=ContextUtil.getString(response);
                                log.info(cont);
                            }
                        });
                    }
                });

            }
        });
    }

    public static void main(String[] args) {
        Spider spider= SpiderManager.instance().create();
        MaimaiCrawler crawler=new MaimaiCrawler(spider);
        crawler.getMaimai();
        spider.start();
    }
}
