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

        Integer totalPage;
        Integer totalCount = questionMapper.count();
        if(totalCount % size == 0){
            totalPage = totalCount/size;
        } else {
            totalPage = totalCount/size + 1;
        }

        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }

        paginationDto.setPagination(totalPage,page);

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

    public PaginationDto list(Integer userId, Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);
        if(totalCount % size == 0){
            totalPage = totalCount/size;
        } else {
            totalPage = totalCount/size + 1;
        }

        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }

//        paginationDto.setPagination(totalCount,page,size);
        paginationDto.setPagination(totalPage,page);

        Integer offset = size * (page-1);
        List<Question> questions = questionMapper.list(userId,offset,size);
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);
        return paginationDto;
    }

    public QuestionDto getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question, questionDto);
        User user = userMapper.findById(question.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }
}
