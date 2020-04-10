package com.ritian.community.dto;

import lombok.Data;

/**
 * @author: wangth_oup
 * @date: 2019-10-26 12:21
 * @description:
 **/
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
