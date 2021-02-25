package com.cdy.basicdata.test;

import com.cdy.basicdata.common.aop.LogAspectAnnotation;
import com.cdy.basicdata.system.domain.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2021/2/24 11:29
 */
@Api(tags = "外观模式测试")
@Slf4j
@RequestMapping("/facade")
@RestController
public class HelloWorldController {

    @Value("${server.port}")
    private Integer port;

    /**
     * 这里提供一个基础的查询服务，通过入参userId查询用户信息。
     * 后续就需要在这里扩展白名单，只有指定用户才可以查询，其他用户不能查询。
     * @param userId
     * @return
     */
    @ApiOperation("查询人员信息")
    @GetMapping("/queryUserInfo")
    public Result queryUserInfo(@ApiParam(name = "userId", value = "用户id",required = true)
                                    @RequestParam String userId,
                                @ApiParam(name = "otherParam", value = "其他",required = true)
                                    @RequestParam String otherParam){
        // 做白名单拦截
        List<String> userList = new ArrayList<>();
        userList.add("1001");
        userList.add("aaaa");
        userList.add("ccc");
        if (!userList.contains(userId)){

            return Result.defineError(444, new UserInfo("1111","不是白名单的!"));
        }
        log.debug("查询人员入参为: {}", userId);
        UserInfo userInfo = new UserInfo("呵呵", 19, "银河系");
        return Result.success(userInfo);
    }


    /**
     * 重构queryUserInfo接口
     */

    @ApiOperation("重构的查询人员信息")
    @LogAspectAnnotation(key = "userId")
    @GetMapping("/getUserInfo")
    public Result getUserInfo(@ApiParam(name = "userId", value = "用户id",required = true)
                                  @RequestParam String userId,
                              @ApiParam(name = "otherParam", value = "其他",required = true)
                                  @RequestParam String otherParam){
        UserInfo userInfo = new UserInfo("呵呵", 19, "银河系");
        return Result.success(userInfo);
    }

}
