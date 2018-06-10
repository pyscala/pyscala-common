package com.learner.entity.model;

import com.learner.entity.BaseModel;
import lombok.Data;

import javax.persistence.Table;

/**
 * Created by liufangliang on 2018/6/3.
 */
@Data
@Table(name = "proxy_warehouse")
public class Proxy extends BaseModel{
    private String host;
    private int port;
    private String userName;
    private String password;
    private String siteName;
    private String category;
    private String location;
    private String uniqueId;

}
