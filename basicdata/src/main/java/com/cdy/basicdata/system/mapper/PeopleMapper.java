package com.cdy.basicdata.system.mapper;

import com.cdy.basicdata.system.domain.param.PageParam;
import com.cdy.basicdata.system.entity.People;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author chendeyin
 * @since 2020-12-29
 */
@Repository
public interface PeopleMapper {

    List<People> listByParams(PageParam pageParam);

    People getById(Integer id);

    /**
     * 修改
     * @param people
     * @return
     */
    Integer updateById(People people);

}
