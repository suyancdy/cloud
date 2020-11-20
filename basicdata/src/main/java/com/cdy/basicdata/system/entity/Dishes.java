package com.cdy.basicdata.system.entity;

import lombok.Data;

/**
 * @Description: 菜品
 * @Author: chendeyin
 * @Date: 2020/11/20 13:41
 */
@Data
public class Dishes {
    private Integer id;
    private String dishesName;
    private String dishesNo;
    private Integer dishesQuantity;

    public Dishes() {
    }

    @Override
    public String toString() {
        return "Dishes{" +
                "id=" + id +
                ", dishesName='" + dishesName + '\'' +
                ", dishesNo='" + dishesNo + '\'' +
                ", dishesQuantity=" + dishesQuantity +
                '}';
    }
}
