package com.ritian.community.service;

import com.ritian.community.dto.PaginationDto;
import com.ritian.community.dto.QuestionDto;
import com.ritian.community.mapper.QuestionMapper;
import com.ritian.community.mapper.UserMapper;
import com.ritian.community.pojo.Question;
import com.ritian.community.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangth_oup
 * @date: 2020-04-10 20:07
 * @description: service在mapper层上，mapper只能查询数据库的信息，service层可以组装
 **/
@Service
public class QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private UserMapper userMapper;

    public PaginationDto qryAll(Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalCount = questionMapper.count();
        paginationDto.setPagination(totalCount,page,size);
        if(page < 1){
            page = 1;
        }
        if(page > paginationDto.getTotalPage()){
            page = paginationDto.getTotalPage();
        }

        Integer offset = size * (page-1);
        List<Question> questions = questionMapper.qryAll(offset,size);
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            //古老的方法，从pojo里get出来然后set到Dto中
//            questionDto.setId(question.getId());
//            questionDto.setTitle(question.getTitle());
//            questionDto.setDescription(question.getDescription());
//            questionDto.setGmtCreate(question.getGmtCreate());
//            questionDto.setGmtModified(question.getGmtModified());
//            questionDto.setCreator(question.getCreator());
//            questionDto.setCommentCount(question.getCommentCount());
//            questionDto.setViewCount(question.getViewCount());
//            questionDto.setLikeCount(question.getLikeCount());
//            questionDto.setTag(question.getTag());
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);
        return paginationDto;
    }
}
