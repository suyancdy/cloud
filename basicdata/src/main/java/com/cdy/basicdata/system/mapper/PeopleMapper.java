package com.cdy.basicdata.system.mapper;

import com.cdy.basicdata.common.BaseMapper;
import com.cdy.basicdata.system.entity.People;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/22 10:10
 * @See: com.cdy.basicdata.system.mapper
 * @Modified:
 */

@Repository
public interface PeopleMapper  extends BaseMapper<People> {

    List<People> listBy();

}
