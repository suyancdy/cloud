package com.cdy.basicdata.test.entity;

import lombok.Data;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2021/3/12 10:20
 */
@Data
public class LabelVolunteer {
    //
    private Integer id;

    //
    private  String  idCard;

    //
    private  Integer idCardIsDelete;

    public LabelVolunteer() {
    }

    public LabelVolunteer(Integer id, String idCard, Integer idCardIsDelete) {
        this.id = id;
        this.idCard = idCard;
        this.idCardIsDelete = idCardIsDelete;
    }
}
