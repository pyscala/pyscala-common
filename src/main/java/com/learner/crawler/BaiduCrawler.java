package com.learner.crawler;

import com.learner.spider.RespContext;
import com.learner.spider.SpiderManager;
import com.learner.util.JSUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.learner.spider.ParserObserver;
import com.learner.spider.Spider;

import java.io.IOException;

/**
 * Created by liufangliang on 2018/1/29.
 */
@Slf4j
public class BaiduCrawler extends AbstractCrawler {
    public BaiduCrawler(Spider spider){
       super(spider);
    }


    public void init(){
        getUrl("http://www.baidu.com",null,new ParserObserver() {
            public void afterRequest(RespContext response) {
                try {
                    String cont=EntityUtils.toString(response.getResponse().getEntity(), Charsets.UTF_8);
                    log.info(cont);
                    Document doc =Jsoup.parse(cont);
                    Element img =doc.select("img[hidefocus=true]").first();
                    String url =img.attr("src").substring(2);
                    log.info(url);
                    getUrl("http://"+url,null,new ParserObserver() {

                        public void afterRequest(RespContext response) {
                            try {
                                String cont=EntityUtils.toString(response.getResponse().getEntity(), Charsets.UTF_8);
                                log.info(cont);
                            } catch (IOException e) {
                                log.info("err"+e);
                            }
                        }
                    });
                } catch (IOException e) {
                    log.info("parser err "+e);
                }
            }
        });
    }

    public static void main(String[] args) {
        Spider spider= SpiderManager.instance().create();
        BaiduCrawler crawler=new BaiduCrawler(spider);
//        crawler.init();
//        spider.start();
        System.out.println(JSUtil.runJs("/Users/liufangliang/IdeaProjects/common/src/main/resources/common/a.js","a",1,2,3));

        log.info(JSUtil.runResourceJs("/common/js/a.js","a",1,2,4));
    }
}
