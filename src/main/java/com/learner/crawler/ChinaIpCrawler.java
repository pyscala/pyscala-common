package com.learner.crawler;

import com.learner.spider.*;
import com.learner.util.ContextUtil;

/**
 * Created by liufangliang on 2018/4/14.
 */
public class ChinaIpCrawler extends AbstractCrawler {

    public ChinaIpCrawler(Spider spider){
        super(spider);
        Site site=new Site();
        site.setFollowRedirect(true);
        this.getSpider().setSite(site);
    }


    public void init(){
        getUrl("http://chinaip.sipo.gov.cn/"
                , null
                , new ParserObserver() {
            public void afterRequest(RespContext response) {
                String html = ContextUtil.getString(response);
//                log.info(html);
            }
        });
    }


    public void login(){
        String [][] params={
                {"errorurl","error.jsp"}
                ,{"NickName",""}
                ,{"url","zljs/index.jsp?navRootID=1861&t=2"}
                ,{"channelid","14,15,16"}
                ,{"name","cnipr"}
                ,{"password","123456"}
                ,{"searchword",""}
                ,{"chanye","1506"}
                ,{"searchitem","ti"}
                ,{"presearchword",""}
        };
        String [][] headers={
                {"Referer","http://chinaip.sipo.gov.cn/"}
                ,{"Content-Type","application/x-www-form-urlencoded"}
                ,{"Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8"}
                ,{"Upgrade-Insecure-Requests","1"}
                ,{"Accept-Encoding","gzip, deflate"}
                ,{"Accept-Language","zh-CN,zh;q=0.9,en;q=0.8"}
        };
        postUrl("http://chinaip.sipo.gov.cn/login", params, null, headers, new ParserObserver() {
            public void afterRequest(RespContext response) {
                String html = ContextUtil.getString(response);
                String statusLine=response.getResponse().getStatusLine().toString();
                log.info(statusLine);
                log.info(html);
            }
        });
    }

//
//    public void getRecordFrame(){
//        getUrl();
//  }

    public void getDetail(){
        String [][] params={
                {"sqh",""},
                {"downloadcol",""},
                {"searchword","分类号=(B63B35/44,E02B17,E21B) and 名称,摘要,主权项+=(平台) or 名称=(海 and 平台) or 名称,摘要,主权项+=调查船 or 分类号=(B63C11,B63G8,'G02B23/22')"}
                ,{"channelid","14"}
                ,{"extension",""}
                ,{"sortfield","RELEVANCE"}
                ,{"searchChannel",""}
                ,{"page",""}
                ,{"j_encoding","UTF-8"}
                ,{"record","2"}
                ,{"channel",""}
                ,{"cizi","2"}
        };

        String [][] headers={
                {"Cookie","JSESSIONID=CAF3C995CAA7D8425338159C59B7400A"}
                ,{"User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36"}
                ,{"Content-Type","application/x-www-form-urlencoded"}
                ,{"Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8"}
        };

        postUrl("http://chinaip.sipo.gov.cn/zljs/Patent-Detail.jsp", params, null, headers, new ParserObserver() {
            public void afterRequest(RespContext response) {
                String html = ContextUtil.getString(response);

//                log.info(html);
            }
        });
    }


    public static void main(String[] args) {
        Spider spider= SpiderManager.instance().create();
        ChinaIpCrawler cl=new ChinaIpCrawler(spider);
        cl.init();
        spider.start();
        cl.login();
        spider.start();

    }
}
