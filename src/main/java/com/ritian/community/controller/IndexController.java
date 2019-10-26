package com.ritian.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: wangth_oup
 * @date: 2019-10-26 8:45
 * @description:
 **/
@Controller
public class IndexController {

    @GetMapping("/")
    public String hello() {
        return "index";
    }

}
