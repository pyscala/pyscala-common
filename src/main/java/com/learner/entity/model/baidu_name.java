package com.learner.entity.model;

import com.learner.entity.BaseModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by liufangliang on 2018/3/4.
 */
@Data
@Entity
@Table(name = "baidu")
public class baidu_name extends BaseModel {

    private String name;
}
