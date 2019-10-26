package com.ritian.community.controller;

import com.ritian.community.dto.AccessTokenDTO;
import com.ritian.community.dto.GithubUser;
import com.ritian.community.util.GithubUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: wangth_oup
 * @date: 2019-10-26 12:04
 * @description:
 **/
@Controller
public class AuthorizeController {

    @Value("${github.client.clientId}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.redirect_uri}")
    private String redirectUri;

    @Autowired
    private GithubUtils githubUtils;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubUtils.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubUtils.githubUser(accessToken);
        System.out.println(githubUser.getId());
        System.out.println(githubUser.getName());
        System.out.println(githubUser.getBio());
        return "index";
    }
}
