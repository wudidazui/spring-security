package com.wangtao.service;

import com.wangtao.domain.ResponseResult;
import com.wangtao.domain.User;

/**
 * @author 001
 * @date 9/16/24
 */
public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
