package com.learner.unicom;

import com.learner.crawler.AbstractCrawler;
import com.learner.spider.RespContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import com.learner.spider.ParserObserver;
import com.learner.spider.Site;

import java.io.IOException;

/**
 * @author : liufangliang
 * @description :
 * @date : 2017\12\28 0028
 */
@Slf4j
public class UnicomNew extends AbstractCrawler {

    public void getBaidu(){
        getUrl("http://www.baidu.com", null,  new ParserObserver() {
            @Override
            public void afterRequest(RespContext respons) {
                HttpResponse response=respons.getResponse();
                log.info(response.getStatusLine().toString());
                log.info(response.getStatusLine().getStatusCode()+"--------statusCode");
                log.info(response.getStatusLine().getReasonPhrase());
                log.info(response.getStatusLine().getProtocolVersion().getProtocol());

                log.info("-------------------");
                for (int i = 0; i < response.getAllHeaders().length; i++) {
                    Header header= response.getAllHeaders()[i];
                    log.info("header "+ i +"----"+header.getName()+":"+header.getValue()+">>>"+header.getElements());
                }

                HttpEntity entity = response.getEntity();
//                log.info(entity.getContentEncoding().getName()+":"+entity.getContentEncoding().getValue());
                log.info(entity.getContentType().getName()+":"+entity.getContentType().getValue());
                log.info(entity.getContentLength()+"");
                try {
                    log.info(entity.getContent().available()+"");
                    log.info(entity.getContent().toString()+"");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(entity!=null){
                    try {
                        String str = EntityUtils.toString(entity);
                        log.info(str);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else {
                    log.info("entity is null !");
                }

            }
        },new Site());
    }

    public static void main(String[] args) {
        new UnicomNew().getBaidu();
    }
}
