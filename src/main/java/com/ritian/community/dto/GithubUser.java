package com.ritian.community.dto;

import lombok.Data;

/**
 * @author: wangth_oup
 * @date: 2019-10-26 12:45
 * @description: 从github拿到的user信息
 **/
@Data
public class GithubUser {
    private Long id;
    private String name;
    private String bio;//描述信息
    private String avatarUrl;//头像地址
}
