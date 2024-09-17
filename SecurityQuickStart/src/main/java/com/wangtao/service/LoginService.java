package com.wangtao.service;

import com.wangtao.domian.ResponseResult;
import com.wangtao.domian.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
