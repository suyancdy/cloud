package com.cdy.basicdata.system.service;

import com.cdy.basicdata.system.domain.param.PageParam;
import com.cdy.basicdata.system.entity.People;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chendeyin
 * @since 2020-12-29
 */
public interface IPeopleService2 {

    /**
     * 修改
     * @param people
     * @return
     */
    Integer updateById2(People people);
}
