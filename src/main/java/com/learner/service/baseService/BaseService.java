package com.learner.service.baseService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liufangliang on 2018/3/20.
 */
@Slf4j
@Service
public class BaseService implements IBaseService {

    @Autowired
    private BaseDao dao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public <T> T update(T model) {
        return dao.update(model);
    }

    public <T> T create(T t) {
        return dao.create(t);
    }

    public <T> void batchCreate(List<T> models) {
        for (T model : models) {
            dao.create(model);
        }
        dao.flush();
        dao.clear();
    }



    public <T> void remove(T model) {
        dao.remove(model);
    }

    public <T> T getById(Class<T> modelClass, Object id) {
        return null;
    }

    public void executeUpdate(String sql) {
        dao.nativeUpdate(sql);
    }

    public List<Object[]> nativeQuery(String sql) {
        return dao.nativeQuery(sql);
    }

    public <T> List<T> nativeQuery(String sql, Class<T> clazz) {
        return dao.nativeQuery(sql, clazz);
    }

    public void nativeUpdate(String sql) {
        dao.nativeUpdate(sql);
    }

}
