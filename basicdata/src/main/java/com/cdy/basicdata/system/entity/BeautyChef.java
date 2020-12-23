package com.cdy.basicdata.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

/**
 * @Description: 厨师
 * @Author: chendeyin
 * @Date: 2020/11/19 17:43
 */
@ApiModel(value = "BeautyChef",description = "厨师")
@Data
public class BeautyChef {

    // 主键
    @ApiModelProperty(value = "主键")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    // 编号
    @ApiModelProperty(value = "编号")
    private String chefNo;

    // 昵称
    @ApiModelProperty(value = "昵称", required = true)
    private String nickname;

    // 真实姓名
    @ApiModelProperty(value = "真实姓名", required = true)
    private String name;

    // 手机号
    @ApiModelProperty(value = "手机号", required = true)
    private String phoneNo;

    // 头像地址
    @ApiModelProperty(value = "头像地址")
    private String avatarUrl;

    @JsonIgnore
    @ApiModelProperty(value = "创建时间")
    private LocalDate createTime;

    @JsonIgnore
    @ApiModelProperty(value = "更新时间")
    private LocalDate updateTime;

    @JsonIgnore
    @ApiModelProperty(value = "创建人")
    private Integer createBy;

    @JsonIgnore
    @ApiModelProperty(value = "更新人")
    private Integer updateBy;

    @JsonIgnore
    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;

    @JsonIgnore
    @ApiModelProperty(value = "版本")
    private String version;

    public BeautyChef() {
    }

    @Override
    public String toString() {
        return "BeautyChef{" +
                "id=" + id +
                ", chefNo='" + chefNo + '\'' +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                ", isDelete=" + isDelete +
                ", version='" + version + '\'' +
                '}';
    }
}
