package com.payno.dubbo.producer.service;

import com.payno.dubbo.api.UserRpcService;
import com.payno.dubbo.dto.UserDto;
import org.springframework.stereotype.Service;

/**
 * @author payno
 * @date 2019/12/25 14:51
 * @description
 */
@Service
public class UserRpcServiceImpl implements UserRpcService {
    public UserDto get(Integer id) {
        return new UserDto();
    }
}
