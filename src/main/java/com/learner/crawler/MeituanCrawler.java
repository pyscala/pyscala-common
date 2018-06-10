package com.learner.crawler;

import com.learner.spider.ParserObserver;
import com.learner.spider.RespContext;
import com.learner.spider.Spider;
import com.learner.spider.SpiderManager;
import com.learner.util.ContextUtil;

/**
 * Created by liufangliang on 2018/5/20.
 */
public class MeituanCrawler extends AbstractCrawler {

    public MeituanCrawler(Spider spider){
        this.setSpider(spider);
    }


    public void init(){
//        getUrl("http://www.meituan.com/jiehun/42248833/", null, new ParserObserver() {
//            @Override
//            public void afterRequest(RespContext response) {
//                String cont=ContextUtil.getString(response);
//                log.info(StringUtils.unicodeToUtf8(cont));
//            }
//        });

        String url2="https://content.95516.com/uci-pre/unionpay/brand/brandCoupon.json";
        String [][] headers=new String[][]{
                {"Content-Type", "application/json;charset=utf-8"}
        };
        Object [] json=new Object[]{"utf-8","{\"cityCd\": \"310000\", \"forNewer\": \"0\", \"forQucikPass\": \"0\", \"forUnionpayWallet\": \"0\",\"industryNo\": \"\", \"industrySubNo\": \"\", \"isLocal\": \"1\", \"isSupplyApplePay\": \"0\",\"isSupplyHCE\": \"0\", \"nfcAndIcQuickPass\": \"0\", \"pageCount\": \"10\", \"pageIndex\": \"2\",\"qrCodePay\": \"0\", \"userLat\": \"31.215419\", \"userLnt\": \"121.420148\"}\n"};
        postUrl(url2, json, headers, new ParserObserver() {
            @Override
            public void afterRequest(RespContext response) {
                log.info(ContextUtil.getString(response));
            }
        });

//        postUrl("https://content.95516.com/uci-pre/unionpay/brand/brandCoupon.json",);
    }

    public static void main(String[] args) {
        Spider spider= SpiderManager.instance().create();
        MeituanCrawler crawler=new MeituanCrawler(spider);
        crawler.init();
        spider.start();
    }
}
