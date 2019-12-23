package com.payno.swagger.api;

import com.google.common.collect.Lists;
import com.payno.swagger.api.dto.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author payno
 * @date 2019/12/23 09:56
 * @description
 */
@RestController
@RequestMapping("api")
@Api(tags = "ApiController",protocols = "http")
public class ApiController {
    @GetMapping("/list")
    @ApiOperation(value = "查询用户列表", notes = "目前仅仅是作为测试，所以返回用户全列表",produces = "application/json")
    public List<UserVo> list() {
        return Lists.newArrayList(
                UserVo.builder().id("001").name("payno").build(),
                UserVo.builder().id("002").name("chad").build()
        );
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除指定用户编号的用户")
    @ApiImplicitParam(name = "id", value = "用户编号", paramType = "query", dataTypeClass = Integer.class, required = true, example = "1024")
    public Boolean delete(@RequestParam("id") Integer id) {
        return true;
    }


}
