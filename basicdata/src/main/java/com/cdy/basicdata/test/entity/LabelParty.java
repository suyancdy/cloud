package com.cdy.basicdata.test.entity;

import lombok.Data;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2021/3/11 17:21
 */
@Data
public class LabelParty {

    //
    private Integer id;

    //
    private  String  idCard;

    //
    private  Integer idCardFlag;

    public LabelParty() {
    }

    public LabelParty(Integer id, String idCard, Integer idCardFlag) {
        this.id = id;
        this.idCard = idCard;
        this.idCardFlag = idCardFlag;
    }
}
