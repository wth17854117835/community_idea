package com.ritian.community.pojo;

import lombok.Data;

/**
 * @author: wangth_oup
 * @date: 2020-04-10 16:41
 * @description: 与数据库中的表--question--对应的Question类
 **/
@Data
public class Question {
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
}
