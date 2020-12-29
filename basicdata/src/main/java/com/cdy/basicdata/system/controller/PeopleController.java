package com.cdy.basicdata.system.controller;


import com.cdy.basicdata.system.domain.param.PageParam;
import com.cdy.basicdata.system.entity.People;
import com.cdy.basicdata.system.service.IPeopleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chendeyin
 * @since 2020-12-29
 */
@Api(tags = "人员")
@Slf4j
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private IPeopleService iPeopleService;

    @ApiOperation("条件列表查询")
    @GetMapping("/selectListByParams")
    public List<People> selectListByParams(@ModelAttribute PageParam pageParam){
        log.info("条件列表查询的参数为: {}", pageParam.toString());
        return iPeopleService.listByParams(pageParam);
    }
}
