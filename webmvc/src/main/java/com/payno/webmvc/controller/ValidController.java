package com.payno.webmvc.controller;

import com.payno.webmvc.web.dto.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author payno
 * @date 2019/11/26 16:38
 * @description
 * JSR
 * @Null 被注释的元素必须为 null
 * @NotNull 被注释的元素必须不为 null
 * @AssertTrue 被注释的元素必须为 true
 * @AssertFalse 被注释的元素必须为 false
 * @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @Size(max=, min=)   被注释的元素的大小必须在指定的范围内
 * @Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内
 * @Past 被注释的元素必须是一个过去的日期
 * @Future 被注释的元素必须是一个将来的日期
 * @Pattern(regex=,flag=) 被注释的元素必须符合指定的正则表达式
 * Hibernate
 * @NotBlank(message =)   验证字符串非null，且长度必须大于0
 * @Email 被注释的元素必须是电子邮箱地址
 * @Length(min=,max=) 被注释的字符串的大小必须在指定的范围内
 * @NotEmpty 被注释的字符串的必须非空
 * @Range(min=,max=,message=) 被注释的元素必须在合适的范围内
 */
@RestController
@Validated
public class ValidController {
    @GetMapping("test1")
    public String test1(
            @NotBlank(message = "{required}") String name,
            @Email(message = "{invalid}") String email) {
        return "success";
    }

    @GetMapping("test2")
    public String test2(@Valid User user) {
        return "success";
    }
}
