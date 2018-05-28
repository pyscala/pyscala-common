package com.learner.service.baseService;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by liufangliang on 2018/3/21.
 */
public interface IBaseService {

    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
    <T> T update(final T t);

    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
    <T> T create(final T t);

    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
    <T> void batchCreate(final List<T> list);

    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
    <T> void remove(final T model);

//    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//    <T> T getOneByFieldValue(Class<T> modelClass, String fieldName, Object value);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    <T> T getById(Class<T> modelClass, final Object id);

//    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
//    <T> T getByIdFromMaster(Class<T> modelClass, final Object id);

//    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
//    Integer executeUpdate(final String hql, Object... params);

//    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//    <T> List<T> listQuery(final String hql, Class<T> modelClass);
//
//    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//    <T> List<T> listQuery(final String hql, Class<T> modelClass, Object... params);

//    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
//    <T> List<T> listQueryFromMaster(String hql, Class<T> modelClass);
//
//    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
//    <T> List<T> listQueryFromMaster(String hql, Class<T> modelClass, Object... params);
//
//    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//    <T> List<T> listQuery(final String hql, Class<T> modelClass, List<Object> params);

    /**
     * 通过传入hql和list，使用in的方式查询
     *
     * @param hql
     * @param modelClass
     * @param params
     * @return
     */
//    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//    <T> List<T> listQueryForIn(final String hql, Class<T> modelClass, List<Object> params);
//
//    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
//    <T> List<T> listQueryFromMaster(String hql, Class<T> modelClass, List<Object> params);
//
//    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//    <T> T singleQuery(final String hql, Class<T> modelClass);
//
//    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//    <T> T singleQuery(final String hql, Class<T> modelClass, Object... params);
//
//    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
//    <T> T singleQueryFromMaster(String hql, Class<T> modelClass);
//
//    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
//    <T> T singleQueryFromMaster(String hql, Class<T> modelClass, Object... params);

//    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
//    void flush();

//    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//    <T> List<T> listQueryForPage(final String hql, Class<T> modelClass, QueryHelper queryHelper);
//
//    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
//    <T> List<T> listQueryForPageFromMaster(String hql, Class<T> modelClass, QueryHelper queryHelper);

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    List<Object[]> nativeQuery(final String sql);

//    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
//    List<Object[]> nativeQueryFromMaster(String sql);

    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
    void nativeUpdate(String sql);

//    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
//    void nativeUpdate(final String sql, Object... params);
//
//    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
//    void nativeUpdate(final String sql, Map<String, Object> paramMap);
//
//    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
//    List<Object> nativeQuerySingleColumn(final String sql);
//
//    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
//    List<Object> nativeQuerySingleColumnFromMaster(String sql);
//
//    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
//    int prc_call(final String sql);
//
//    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
//    int nativeQueryGetCount(String sql);
//
//    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
//    int nativeQueryGetInt(String sql);
//
//    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
//    int nativeQueryGetCountFromMaster(String sql);
//
//    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//    <T> int getCountByFieldValue(Class<T> modelClass, String fieldName, Object value);

//    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
//    <T> int getCountByFieldValueFromMaster(Class<T> modelClass, String fieldName, Object value);
//
//    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
//    void nativeUpdateSingleFieldWithVersionAndUpdated(String tableName, String fieldName, String valueWithQuoteIfNeeded, Long id);
//
//    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//    <T> T queryObject(final String hql, Class<T> modelClass, Object... params);
//
//    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
//    <T> T queryObjectFromMaster(String hql, Class<T> modelClass, Object... params);

}
