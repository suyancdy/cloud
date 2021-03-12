package com.cdy.basicdata.test.mapper;

import com.cdy.basicdata.test.entity.LabelParty;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2021/3/11 17:30
 */
@Repository
public interface LabelPartyMapper {

    //
    List<LabelParty> listByParams();

    //
    Integer updateIdCardFlag(Integer id);
}
