package com.medimpact.medeasy.dao;

import java.util.List;

/** 
* @author Crystal E-mail: 
* @version 创建时间：2017年9月15日 
* 类说明 
*/
public interface CommonDao<T>{



    /**
     * 添加
     *
     * @param
     * @return
     */
    void insert(T t);

    /**
     * 删除
     *
     * @param id
     */
    int delete(Long id);

    /**
     * 更新
     *
     * @param
     */
    int update(T t);

    /**
     * 列表
     *
     * @param
     * @return
     */
    List<T> list(T t);

    /**
     * 总数
     *
     * @param
     * @return
     */
    long selectCount(T t);

/*    *//**
     * 批量
     *
     * @return
     *//*
    @Flush
    List<BatchResult> flush();*/




}
