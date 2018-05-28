package com.learner.entity.model;

import com.learner.entity.BaseModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by liufangliang on 2018/3/21.
 */
@Entity
@Data
@Table(name = "maimai_user")
public class MaimaiUser extends BaseModel {
    private String userName;
    private String age;
    private String sex;
    private String phone;
    private String educationBackground;
    private Integer workingYears;
}
