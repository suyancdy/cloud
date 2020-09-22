package com.cdy.basicdata.system.controller;

import com.cdy.basicdata.system.entity.People;
import com.cdy.basicdata.system.mapper.PeopleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
