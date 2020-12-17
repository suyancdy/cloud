package com.cdy.basicdata.system.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/11/19 10:24
 */
@Data
public class User {

    private Integer id;
    private String userNo;
    private String name;
    private Integer age;


    public User() {
    }

    public User(Integer id, String userNo, String name, Integer age) {
        this.id = id;
        this.userNo = userNo;
        this.name = name;
        this.age = age;
    }
}
