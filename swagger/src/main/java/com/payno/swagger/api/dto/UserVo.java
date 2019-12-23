package com.payno.swagger.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author payno
 * @date 2019/12/23 09:59
 * @description
 */
@Data
@Builder
@ApiModel("用户 VO")
public class UserVo {
    @ApiModelProperty(value = "用户编号", required = true, example = "1024")
    public String id;
    @ApiModelProperty(value = "账号", required = true, example = "yudaoyuanma")
    public String name;
}
