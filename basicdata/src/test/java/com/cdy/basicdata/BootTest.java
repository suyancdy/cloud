package com.cdy.basicdata;

import com.cdy.basicdata.common.utils.RedisUtils;
import com.cdy.basicdata.system.domain.param.PageParam;
import com.cdy.basicdata.system.entity.People;
import com.cdy.basicdata.system.entity.User;
import com.cdy.basicdata.system.mapper.PeopleMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/20 14:41
 * @See: com.cestc.basicdata
 * @Modified:
 */
@Slf4j
@SpringBootTest
public class BootTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PeopleMapper peopleMapper;

    @Test
    public void test() throws Exception {
        PageParam pageParam = new PageParam();
        pageParam.setPageNum(2);
        pageParam.setPageSize(3);
        log.info("分页参数为: {}", pageParam.toString());
        List<People> peopleList = peopleMapper.listByParams(pageParam);
        peopleList.forEach(
                i -> { log.info("====: {}", i.toString()); }
        );

    }
}
