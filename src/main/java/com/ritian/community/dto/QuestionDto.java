package com.ritian.community.dto;

import com.ritian.community.pojo.User;
import lombok.Data;

/**
 * @author: wangth_oup
 * @date: 2020-04-10 20:05
 * @description:
 **/
@Data
public class QuestionDto {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
