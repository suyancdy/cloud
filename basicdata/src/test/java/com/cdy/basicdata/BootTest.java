package com.cdy.basicdata;

import com.cdy.basicdata.common.utils.RedisUtils;
import com.cdy.basicdata.system.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    private RedisUtils redisUtils;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test() throws Exception {
        User user = new User(1, "123", "哈哈哈哈哈哈");
        log.info("===: {}", user.toString());
        log.info("参数为：{}", objectMapper.writeValueAsString(user));

    }


    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integerList.add(i);
        }

        List<Integer> resultList = integerList.subList(1, 5);
        log.debug("-----: {}", resultList);
        log.debug("是否是空集合: {}", resultList.isEmpty());

        List<Integer> integerList2 = null;
        log.debug("是否是空集合: {}", CollectionUtils.isEmpty(integerList2));
        
    }

    /**
     * 开始分页
     *
     * @param list 待分页的list
     * @param pageNum  第几页
     * @param pageSize 每页多少条数据
     * @return
     */
    private List paging(List list, Integer pageNum, Integer pageSize) {
        if (list == null) {
            return null;
        }
        Integer size = list.size(); // 记录总数
        if (size == 0) {
            return null;
        }
        List resultList = list.subList(pageSize * (pageNum - 1), ((pageSize * pageNum) > size ? size : (pageSize * pageNum)));
        return resultList;
    }
}
