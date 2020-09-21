package com.cestc.basicdata.system.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/15 10:59
 * @See: com.cestc.basicdata.system.entity
 * @Modified:
 */
@Data
@Table(name="tb_people")
public class People  implements Serializable {
    private Integer id;
    private String peopleNo; // 编号
    private String name; // 姓名
    private String sex; // 性别
    private Integer age; // 年龄
    private Date birthday; // 出生日期


}
