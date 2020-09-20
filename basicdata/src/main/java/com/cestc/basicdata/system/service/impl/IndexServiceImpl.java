package com.cestc.basicdata.system.service.impl;

import com.cestc.basicdata.system.entity.People;
import com.cestc.basicdata.system.service.IndexService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class IndexServiceImpl  implements IndexService {

    @Override
    public String fun(String value, People people000) {

        return  value + "ppppppppp";
    }
}
