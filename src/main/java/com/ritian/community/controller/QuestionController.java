package com.ritian.community.controller;

import com.ritian.community.dto.QuestionDto;
import com.ritian.community.mapper.QuestionMapper;
import com.ritian.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * @author: wangth_oup
 * @date: 2020-04-20 16:45
 * @description:
 **/
@Controller
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @GetMapping(value = "question/{id}")
    public String question(@PathVariable(name = "id") Integer id, Model model){

        QuestionDto questionDto = questionService.getById(id);
        model.addAttribute("question",questionDto);
        return "question";
    }
}
