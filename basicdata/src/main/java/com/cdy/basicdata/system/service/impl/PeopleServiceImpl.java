package com.cdy.basicdata.system.service.impl;

import com.cdy.basicdata.system.domain.param.PageParam;
import com.cdy.basicdata.system.entity.People;
import com.cdy.basicdata.system.mapper.PeopleMapper;
import com.cdy.basicdata.system.service.IPeopleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chendeyin
 * @since 2020-12-29
 */
@Slf4j
@Service
public class PeopleServiceImpl implements IPeopleService {

    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public List<People> listByParams(PageParam pageParam) {
        pageParam.setOffSet();
        log.info("条件列表查询的参数为: {}", pageParam.toString());

        log.debug("---");

        People people = peopleMapper.getById(1);

        log.debug("---");

        return peopleMapper.listByParams(pageParam);
    }

    @Override
    public People getById(Integer id) {

        return null;
    }

    @Override
    public PageInfo<People> pageInfoByParams(PageParam pageParam) {

        return null;
    }
}
