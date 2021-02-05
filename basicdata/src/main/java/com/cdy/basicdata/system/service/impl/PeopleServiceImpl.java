package com.cdy.basicdata.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.cdy.basicdata.system.domain.param.PageParam;
import com.cdy.basicdata.system.entity.People;
import com.cdy.basicdata.system.mapper.PeopleMapper;
import com.cdy.basicdata.system.service.IPeopleService;
import com.cdy.basicdata.system.service.IPeopleService2;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
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
public class PeopleServiceImpl implements IPeopleService {

    @Autowired
    private PeopleMapper peopleMapper;

    @Autowired
    private IPeopleService2 iPeopleService2;


    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<People> listByParams(PageParam pageParam) {
        pageParam.setOffSet();
        log.info("条件列表查询的参数为: {}", pageParam.toString());
        return peopleMapper.listByParams(pageParam);
    }

    /**
     * 查询人员详情
     *
     * @param id
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public People getById(Integer id) {
        log.debug("查询人员详情的参数: {}", id);
        People people = peopleMapper.getById(id);
        log.debug("===: {}", JSON.toJSONString(people));
        log.debug("睡眠12秒");
        try {
            Thread.sleep(12 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("睡眠12秒结束");
        return people;
    }

    @Override
    public PageInfo<People> pageInfoByParams(PageParam pageParam) {

        return null;
    }


    @Transactional
    @Override
    public Integer updateById(People people) throws InterruptedException {
        int affectedRows = peopleMapper.updateById(people);
        Thread.sleep(12 * 1000);
        //   log.debug("一个异常: {}", 1 / 0);
        return affectedRows;
    }


}
