package com.cdy.basicdata.system.controller;

import com.cdy.basicdata.system.entity.BeautyChef;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 厨师的控制层
 * @Author: chendeyin
 * @Date: 2020/12/23 9:34
 */
@Api(tags = "厨师")
@Slf4j
@RequestMapping("/chef")
@RestController
public class BeautyChefController {

    @ApiOperation("新增厨师")
    @PostMapping("/insert")
    public Integer insert(@RequestBody BeautyChef beautyChef){
      log.info("新增一个厨师为：{}", beautyChef.toString());

        return  0;
    }

}
