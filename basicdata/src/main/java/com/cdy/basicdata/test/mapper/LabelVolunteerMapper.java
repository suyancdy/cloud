package com.cdy.basicdata.test.mapper;

import com.cdy.basicdata.test.entity.LabelVolunteer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2021/3/12 10:22
 */
@Repository
public interface LabelVolunteerMapper {
    // 列出所有志愿者
    List<LabelVolunteer> listByParams();

    //
    Integer updateIdCardIsDelete(Integer id);
}
