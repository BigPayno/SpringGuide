package com.payno.dubbo.api;

import com.payno.dubbo.dto.UserDto;

/**
 * @author payno
 * @date 2019/12/25 14:41
 * @description
 */
public interface UserRpcService {
    /**
     * 根据指定用户编号，获得用户信息
     *
     * @param id 用户编号
     * @return 用户信息
     */
    UserDto get(Integer id);
}
