package com.cdy.basicdata.system.service.impl;

import com.cdy.basicdata.system.domain.param.PageParam;
import com.cdy.basicdata.system.entity.People;
import com.cdy.basicdata.system.mapper.PeopleMapper;
import com.cdy.basicdata.system.service.IPeopleService;
import com.cdy.basicdata.system.service.IPeopleService2;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chendeyin
 * @since 2020-12-29
 */
@Slf4j
@Service
public class PeopleServiceImpl2 implements IPeopleService2 {

    @Autowired
    private PeopleMapper peopleMapper;


    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Integer updateById2(People people){
        int affectedRows = peopleMapper.updateById(people);
        return affectedRows;
    }

}
