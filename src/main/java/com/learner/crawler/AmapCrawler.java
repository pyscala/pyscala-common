package com.learner.crawler;

import com.learner.spider.ParserObserver;
import com.learner.spider.RespContext;
import com.learner.spider.Spider;
import com.learner.spider.SpiderManager;
import com.learner.util.ContextUtil;
import com.learner.util.JSUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by liufangliang on 2018/1/29.
 */
@Slf4j
public class AmapCrawler extends AbstractCrawler {
    public AmapCrawler(Spider spider) {
        super(spider);
    }


    public void init() {
        getUrl("https://www.amap.com/", null, new ParserObserver() {
            @Override
            public void afterRequest(RespContext response) {
//                log.info(ContextUtil.getString(response));


                String [][] headers=new String[][]{
                        {"X-Requested-With","XMLHttpRequest"},
                        {"Referer","https://www.amap.com/search?query=%E8%82%AF%E5%BE%B7%E5%9F%BA&city=310000&geoobj=121.033181%7C30.876997%7C122.35156%7C31.608483&zoom=10"},
                };
                getUrl("https://www.amap.com/service/poiInfo?query_type=TQUERY&pagesize=20&pagenum=2&qii=true&cluster_state=5&need_utd=true&utd_sceneid=1000&div=PC1000&addr_poi_merge=true&is_classify=true&zoom=10&city=310000&geoobj=121.033181%7C30.876997%7C122.35156%7C31.608483&keywords=%E8%82%AF%E5%BE%B7%E5%9F%BA",
                        null, new ParserObserver() {
                            @Override
                            public void afterRequest(RespContext response) {
                                log.info(ContextUtil.getString(response));
                            }
                        });
            }
        });

    }

    public static void main(String[] args) {
        Spider spider = SpiderManager.instance().create();
        AmapCrawler crawler = new AmapCrawler(spider);
        crawler.init();
        spider.start();
//      System.out.println(JSUtil.runJs("/Users/liufangliang/IdeaProjects/common/src/main/resources/common/a.js","a",1,2,3));
//      log.info(JSUtil.runResourceJs("/common/js/a.js","a",1,2,4));
    }
}
