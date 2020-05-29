package com.mcoding.emis.goods.common.service;

import java.io.Serializable;
import java.util.ArrayList;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.member.common.CommonResult;

/**
 * Created by Administrator on 2014/6/4.
 */
public interface BaseService<T extends Serializable, M extends Serializable> {
    CommonResult<M> addObj(T t);

    CommonResult<M> deleteObjById(String id);

    CommonResult<M> modifyObj(T t);

    CommonResult<T> queryObjById(String id);

    CommonResult<ArrayList<T>> queryListObj(String... args);

    CommonResult<PageView<T>> queryObjByPage(int pageNo, int pageSize);

    PageView<T> queryObjByPage(String iDisplayStart, String iDisplayLength);

}
