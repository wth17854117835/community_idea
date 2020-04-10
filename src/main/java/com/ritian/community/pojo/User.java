package com.ritian.community.pojo;

import lombok.Data;

/**
 * @author: wangth_oup
 * @date: 2019-11-03 14:25
 * @description: 与数据库中的表--USER--对应的User类
 **/
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
}
