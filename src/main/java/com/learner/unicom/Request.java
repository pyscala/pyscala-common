package com.learner.unicom;

import java.io.Serializable;
import java.util.Map;

public class Request implements Serializable{

    private String statusLine;
    private Map<String,String> headers;
    private Map<String,Object> extras;




}
