package com.learner.unicom;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.learner.spider.Spider;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class UnicomCrawler extends Spider {

    private final static Logger log =LoggerFactory.getLogger(UnicomCrawler.class);

    private String phoneNo="14531620693000000";
    private String password ="123321000000";
    public void getHtml(){

        try {
            String url = "http://m.client.10010.com/mobileService/login.htm";
            CloseableHttpClient client = HttpClients.createDefault();
//            RequestBuilder builder = RequestBuilder.post();
//            HttpPost post = new HttpPost(url);
//            builder.addHeader("Content-Type","application/x-www-form-urlencoded");
//            builder.addHeader("Host","m.client.10010.com");
//            builder.addHeader("User-Agent","");
//            builder.addHeader("Accept-Encoding","gzip");

            String [][] headers={{"Content-Type","application/x-www-form-urlencoded"},
                    {"Host","m.client.10010.com"},
                    {"User-Agent",""},
                    {"Accept-Encoding","gzip"}};
            String [][] params= {
                    {"deviceOS","android7.0"},
                    {"mobile",unicom.encrypt(phoneNo)},
                    {"netWay","WIFI"},
                    {"deviceCode","864621031289901"},
                    {"isRemberPwd","false"},
                    {"version","android@5.51"},
                    {"deviceId","864621031289901"},
                    {"pushPlatform","HUAWEI"},
                    {"password",unicom.encrypt(password)},
                    {"platformToken",""},
                    {"keyVersion",""},
                    {"provinceChanel","B360001"},
                    {"appId","ChinaunicomMobileBusiness"},
                    {"deviceModel","DUK-AL20"},
                    {"deviceBrand","HUAWEI"},
                    {"timestamp",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())}
            };
//            HttpUriRequest request= postUrl(url,headers,params,new Site());
            HttpUriRequest request=null;
            log.info(request.toString());
            HttpResponse response = client.execute(request);

// 服务器返回码
            int status_code = response.getStatusLine().getStatusCode();
            log.info("status_code = " + status_code);

            // 服务器返回内容
            String respStr = null;
            HttpEntity entity = response.getEntity();
            if(entity != null) {
                log.info(response.getStatusLine().toString());
                respStr = EntityUtils.toString(entity, "UTF-8");
            }
            log.info("respStr = " + respStr);
            Document doc = Jsoup.parse(respStr);
            String name = doc.select("input[name=rsv_idx]").val();
            log.info(name);
            // 释放资源
            EntityUtils.consume(entity);
        }catch (Exception e ){
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {

        new UnicomCrawler().getHtml();
    }
}
