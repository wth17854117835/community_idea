package com.ritian.community.dto;

/**
 * @author: wangth_oup
 * @date: 2019-10-26 12:45
 * @description: 从github拿到的user信息
 **/
public class GithubUser {

    private Long id;
    private String name;
    private String bio;//描述信息

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
