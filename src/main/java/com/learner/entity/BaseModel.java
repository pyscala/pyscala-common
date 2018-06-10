package com.learner.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by liufangliang on 2018/3/4.
 */
@MappedSuperclass
@Data
public class BaseModel implements Serializable {

    private static final long serializableUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id = -1l;

    @Column(nullable = false, updatable = false)
    protected Date created=new Date();

    @PrePersist
    protected void onCreate() {
        if (created == null) {
            created = new Date();
        }
    }
}
