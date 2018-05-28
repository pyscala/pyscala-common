package com.learner.service.baseService;

import com.learner.entity.BaseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by liufangliang on 2018/3/21.
 */
@Slf4j
@Component
public class BaseDao {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public <T> T create(final T model){
        this.em.persist(model);
        if(model instanceof BaseModel){
            log.info("create model  {} id is {}",model.getClass().getName(),((BaseModel) model).getId());
        }
        return model;
    }

    public <T> T update(final T model){
        return  this.em.merge(model);
    }

    public <T> void remove(final T model) {
        if (model instanceof com.learner.entity.BaseModel) {
            log.info("deleted model {} id {}", model.getClass().getSimpleName(), ((BaseModel) model).getId());
        }
        this.em.remove(this.em.merge(model));
    }
    public void flush(){
        em.flush();
    }

    public void clear(){
        em.clear();
    }

    public void close(){
        em.close();
    }

    public void nativeUpdate(String sql){
         em.createNativeQuery(sql).executeUpdate();
    }

    public List<Object[]> nativeQuery(final String sql) {
        Query query = this.em.createNativeQuery(sql);
        return query.getResultList();
    }

    public <T> List<T> nativeQuery(final String sql, Class<T> modelClass) {
        Query query = this.em.createNativeQuery(sql, modelClass);
        long ts1 = System.currentTimeMillis();
        List<T> list = query.getResultList();
        long ts2 = System.currentTimeMillis();
        long diff = ts2 - ts1;
        log.info("nativeQuery - {}ms - {}",diff,sql);
        return list;
    }


}
