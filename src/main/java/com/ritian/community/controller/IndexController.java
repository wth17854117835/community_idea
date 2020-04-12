package com.ritian.community.controller;

import com.ritian.community.dto.PaginationDto;
import com.ritian.community.dto.QuestionDto;
import com.ritian.community.mapper.QuestionMapper;
import com.ritian.community.mapper.UserMapper;
import com.ritian.community.pojo.Question;
import com.ritian.community.pojo.User;
import com.ritian.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: wangth_oup
 * @date: 2019-10-26 8:45
 * @description:
 **/
@Controller
public class IndexController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "2") Integer size) {
        //1.登陆时 将user信息insert数据库
        //2.将token(UUID随机生成)添加到页面你的cookie里
        //3.服务器重启时打开index页面，从数据库中根据token查询相关记录，有->直接放到session里，页面直接获取显示session.name 达到不用重新登录的效果
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length !=0) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        PaginationDto paginationDto = questionService.qryAll(page,size);
        model.addAttribute("paginationDto",paginationDto);
        return "index";
    }

//    @GetMapping("/hello")
//    public String hello() {
//        return "hello";
//    }

}
