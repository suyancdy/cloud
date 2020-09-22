package com.cdy.basicdata.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
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
    private static final long serialVersionUID = 7894347828930850066L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;
    private String peopleNo; // 编号
    private String name; // 姓名
    private String sex; // 性别
    private Integer age; // 年龄

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date birthday; // 出生日期


}
