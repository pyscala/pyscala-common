package com.learner.unicom;

import com.alibaba.fastjson.JSONObject;
import com.learner.crawler.AbstractCrawler;
import com.learner.spider.RespContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.EntityUtils;
import com.learner.spider.ParserObserver;
import com.learner.spider.Site;


/**
 * Created by liufangliang on 2018/1/11.
 */
@Slf4j
public class AihuishouSpider extends AbstractCrawler {

    public void getHtml() {
        getUrl("http://gw.aihuishou.com/app-portal/product/getpricepropertyinfo?appId=10001&productId=17461&sign=97b906a489c0b7c0d2667b5e0cad2254&timestamp=1515637717&token=f9a33a38-c82d-41a7-b40e-21117db5978f"
                , new String[][]{{"User-Agent", "Dalvik/2.1.0 (Linux; U; Android 7.0; DUK-AL20 Build/HUAWEIDUK-AL20)"}}, new ParserObserver() {

                    public void afterRequest(RespContext response) {
                        try {
                            System.out.println(EntityUtils.toString(response.getResponse().getEntity(), "utf-8"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    public void getPrice() {
        JSONObject json = new JSONObject();
        json.put("productId", 17461);
        json.put("pricePropertyValues", "[3212,2021,2014,2452,2072,2100,2125,2118,2114,2067,2107,2135,3169,2108,2027,2045,2102,2104,2129,2808]");
        json.put("cityId", 1);

        postUrl("http://gw.aihuishou.com/app-portal/product/inquiry?appId=10001&sign=ced8687c9c25a3ac41275926fd19194b&timestamp=1515637737&token=f9a33a38-c82d-41a7-b40e-21117db5978f"
                , new String[][]{
                        {"{\"productId\":17461,\"pricePropertyValues\":[3212,2021,2014,2452,2072,2100,2125,2118,2114,2067,2107,2135,3169,2108,2027,2045,2102,2104,2129,2808],\"cityId\":1}", ""}
                }, null, new String[][]{{"Content-Type", "application/json; charset=utf-8"}
                        , {"User-Agent", "Dalvik/2.1.0 (Linux; U; Android 7.0; DUK-AL20 Build/HUAWEIDUK-AL20)"}
                        , {"Content-Length", "154"}}
                , new ParserObserver() {

                    public void afterRequest(RespContext response) {
                        try {
                            System.out.println(EntityUtils.toString(response.getResponse().getEntity(), "utf-8"));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Site());
    }

    public static void main(String[] args) {
        AihuishouSpider spider = new AihuishouSpider();
        spider.getHtml();
        spider.getPrice();
    }
}
