package com.wangtao.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    @PreAuthorize("@huanfEX.huanfHasAuthority('system:dept:list')")
    public String hello(){
        return "1111";
    }
}
