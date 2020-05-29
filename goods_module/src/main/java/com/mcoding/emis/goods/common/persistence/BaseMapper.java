package com.mcoding.emis.goods.common.persistence;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2014/6/4.
 * 提供基本的增删改查方法
 */
public interface BaseMapper<T extends Serializable> {
    void add(T t);

    void delete(String id);

    void update(T t);

    T queryById(String id);

    List<T> queryByParentId(String parentId);

    List<T> queryByPage(T t);
}
