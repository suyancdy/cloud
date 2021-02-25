package com.cdy.basicdata.system.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 响应结果
 */
@ApiModel(description = "响应结果")
@Data
public class Result {

    // 是否成功
    private Boolean success;

    // 响应码
    private Integer code;

    // 消息
    private String msg;

    private Object data;

    public Result() {
    }

    public Result(Boolean success, Integer code, String msg, Object data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @ApiModel(description = "列表")
    @lombok.Data
    public static class Data {
        //
        private Integer intTotal;

        private Long longTotal;

        private Object rows;

        public Data() {
        }

        public Data(Integer intTotal, Long longTotal, Object rows) {
            this.intTotal = intTotal;
            this.longTotal = longTotal;
            this.rows = rows;
        }
    }



    public static Result success(Object data){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("正常");
        result.setData(data);
        return result;
    }

    public static Result defineError(Integer code, Object data){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg("异常");
        result.setData(data);
        return result;
    }


}
