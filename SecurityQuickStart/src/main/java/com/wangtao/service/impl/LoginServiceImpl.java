package com.wangtao.service.impl;

import com.wangtao.domian.LoginUser;
import com.wangtao.domian.ResponseResult;
import com.wangtao.domian.User;
import com.wangtao.service.LoginService;
import com.wangtao.utils.JwtUtil;
import com.wangtao.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());

        //获取AuthenticationManager的authenticate方法来进行用户认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        System.out.println(authenticate);
        //判断上面那行的authenticate是否为null，如果是则认证没通过，就抛出异常
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        HashMap<String, String> map = new HashMap<>();
        map.put("token",jwt);
    //        redisCache.setCacheObject("login:"+userid,loginUser);
        return new ResponseResult(200,"登录成功",map);

    }

    @Override
    public ResponseResult logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + userid);
        return new ResponseResult(200,"注销成功");
    }
}
