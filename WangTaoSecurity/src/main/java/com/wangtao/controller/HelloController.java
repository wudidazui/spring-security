package com.wangtao.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 001
 * @date 9/16/24
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    @PreAuthorize("@ex.hasAuthority('system:test:index')")
    public String hello(){
        return "hello";
    }
}
