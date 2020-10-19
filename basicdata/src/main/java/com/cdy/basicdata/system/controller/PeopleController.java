package com.cdy.basicdata.system.controller;

import com.cdy.basicdata.common.core.AjaxResult;
import com.cdy.basicdata.system.entity.People;
import com.cdy.basicdata.system.mapper.PeopleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/22 15:10
 * @See: com.cdy.basicdata.system.controller
 * @Modified:
 */
@Slf4j
@Controller
@RequestMapping("/people")
public class PeopleController {


    @Autowired
    private PeopleMapper peopleMapper;


    @GetMapping("/getDetail/{id}")
    @ResponseBody
    public AjaxResult getDetail(@PathVariable("id") Integer id){





        People people =  peopleMapper.selectByPrimaryKey(id);
        return AjaxResult.success(people);
    }


    @PostMapping("/insert")
    @ResponseBody
    public Integer insertPeople(@RequestBody People people){
        peopleMapper.insert(people);
        return  people.getId();
    }


    @PostMapping("/list")
    @ResponseBody
    public List<People> listPeople(){
        Example example = new Example(People.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", "你好");
       return peopleMapper.selectByExample(example);
    }

}
