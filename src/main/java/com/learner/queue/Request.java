package com.learner.queue;

import org.omg.CORBA.NameValuePair;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class Request implements Serializable,Comparable<Request>{

    private String charset;
    private String url;
    private String postXml;
    private String postContentType;
    private String postContentTypeCharSet;
    private String method;
    private boolean useProxy;

    private Map<String,Object> extras;
    private Map<String,String> haders;

    private NameValuePair[] nameValuePairs;

    private long priority;

    private char primary;
    private int sencondary;

    private Collection<Base.BaseLisener> baseLiseners;

    public Request(){

    }

    public Request(char primary, int sencondary, Base.BaseLisener item) {


    }


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        if(!url.equals(request.url)) return false;
        return true;
    }

    @Override
    public int hashCode(){
        return url.hashCode();
    }

    @Override
    public int compareTo(Request o) {
        if(primary > o.primary){
            return 1;
        }
        if(primary == o.primary){
            if(sencondary > o.sencondary){
                return 1;
            } else if(sencondary == o.sencondary){
                return 0;
            }
        }
        return -1;
    }
}
