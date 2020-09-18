package com.cestc.peoman.service.feign.error;

import com.cestc.peoman.service.feign.BasicDataService;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/9 16:36
 * @See: com.cestc.peoman.service.feign.error
 * @Modified:
 */
@Component
public class BasicDataServiceHystrix  implements BasicDataService {

    @Override
    public String feginPort() {
        return "服务不可用！";
    }
}
