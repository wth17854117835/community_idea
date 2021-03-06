package com.ritian.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: wangth_oup
 * @date: 2020-04-08 10:02
 * @description: 测试
 **/
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name="name") String name, Model model){
        model.addAttribute("name",name);
        return "hello";
    }

}
