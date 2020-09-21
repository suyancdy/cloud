package com.cdy.basicdata.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/21 15:05
 * @See: com.cestc.basicdata.common
 * @Modified:
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper {
}
