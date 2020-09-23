package com.cdy.basicdata.system.service.impl;

import com.cdy.basicdata.system.entity.People;
import com.cdy.basicdata.system.service.IndexService;
import org.springframework.stereotype.Service;


@Service
public class IndexServiceImpl  implements IndexService {



    @Override
    public String fun(String value, People people000) {

        return  value + "ppppppppp";
    }
}
