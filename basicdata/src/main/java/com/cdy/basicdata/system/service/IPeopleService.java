package com.cdy.basicdata.system.service;

import com.cdy.basicdata.system.domain.param.PageParam;
import com.cdy.basicdata.system.entity.People;
import com.baomidou.mybatisplus.extension.service.IService;
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
public interface IPeopleService  {
    List<People> listByParams(PageParam pageParam);
    People getById(Integer id);

    PageInfo<People> pageInfoByParams(PageParam pageParam);
}
