package com.cdy.basicdata.system.domain.param;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 分页参数
 * @Author: chendeyin
 * @Date: 2020/12/29 14:20
 */
@ApiModel(value = "PageParam")
public class PageParam {

    @ApiModelProperty(required = true,value = "页数")
    private Integer pageNum = 1;
    @ApiModelProperty(required = true,value = "每页大小")
    private Integer pageSize = 10;

    @JsonIgnore
    private Integer offSet = (pageNum - 1) * pageSize;

    public PageParam() {
        this.offSet = (this.pageNum - 1) * this.pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffSet() {
        return offSet;
    }

    public void setOffSet() {
        this.offSet = (this.pageNum - 1) * this.pageSize;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", offSet=" + offSet +
                '}';
    }
}
