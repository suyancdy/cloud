package com.cestc.peoman.service.feign;

import com.cestc.peoman.service.feign.error.BasicDataServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/9 16:05
 * @See: com.cestc.peoman.service.feign
 * @Modified:
 */
@FeignClient(value = "basicData", fallback = BasicDataServiceHystrix.class)
public interface BasicDataService {

    @ResponseBody
    @RequestMapping("/index/port")
    String feginPort();

}
