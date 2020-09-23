package com.cdy.basicdata.common.core;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/23 10:01
 * @See: com.cdy.basicdata.common.core
 * @Modified:
 */
public class AjaxResult {

    @Override
    public String toString() {
        return "AjaxResult{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    private Boolean success = true;
    // 详见阿里巴巴开发手册错误码
    private String code;

    private String message;

    private Object data;

    /**
     * @description: 空参
     */
    public AjaxResult() {
    }

    public AjaxResult(Boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public AjaxResult(Boolean success, String code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static AjaxResult success() {
        return new AjaxResult( true, Code.SUCCESS.getStateCode(), Code.SUCCESS.getDes());
    }

    public static AjaxResult success(Object object) {
        return new AjaxResult( true, Code.SUCCESS.getStateCode(), Code.SUCCESS.getDes(), object);
    }

    /**
     * @description:
     * @author: chendeyin
     * @date: 2020/9/23 15:16
     */
    public class Data {

        @Override
        public String toString() {
            return "Data{" +
                    "count=" + count +
                    ", rows=" + rows +
                    '}';
        }

        private int count;
        private Object rows;

        public Data() {
        }

        public Data(int count, Object rows) {
            this.count = count;
            this.rows = rows;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public Object getRows() {
            return rows;
        }

        public void setRows(Object rows) {
            this.rows = rows;
        }
    }


    public enum Code {

        SUCCESS("00000", "一切正常");

        private String stateCode;
        private String des;

        Code(String stateCode, String des) {
            this.stateCode = stateCode;
            this.des = des;
        }

        public String getStateCode() {
            return stateCode;
        }

        public void setStateCode(String stateCode) {
            this.stateCode = stateCode;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        @Override
        public String toString() {
            return "Code{" +
                    "stateCode='" + stateCode + '\'' +
                    ", des='" + des + '\'' +
                    '}';
        }
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
