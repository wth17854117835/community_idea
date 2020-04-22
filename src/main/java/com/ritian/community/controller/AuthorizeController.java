package com.ritian.community.controller;

import com.ritian.community.dto.AccessTokenDTO;
import com.ritian.community.dto.GithubUser;
import com.ritian.community.mapper.UserMapper;
import com.ritian.community.pojo.User;
import com.ritian.community.service.UserService;
import com.ritian.community.util.GithubUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * @author: wangth_oup
 * @date: 2019-10-26 12:04
 * @description: 登录github认证授权。配置文件注入2种方式：
 *      (1)@ConfigurationProperties(prefix = "")适用于单独编写javaBean来和配置文件映射。
 *      (2)@Value(${}) 适用于业务逻辑需要获取配置文件中的某项值，复杂类型的获取不到，例如map,list
 **/
@Controller
//@ConfigurationProperties(prefix = "github.client")
public class AuthorizeController {

    @Value("${github.client.clientId}")
    private String clientId;
//    private String clientId = "74897f8eb3f06d9a9731";
    @Value("${github.client.secret}")
    private String clientSecret;
//    private String clientSecret = "ef36072b2c9b057abf7a98f530456db805386196";
    @Value("${github.client.redirect_uri}")
    private String redirectUri;
//    private String redirectUri = "http://localhost:8887/callback";

    @Autowired
    private GithubUtils githubUtils;
    @Resource
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletRequest request,
                           HttpServletResponse response){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubUtils.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubUtils.githubUser(accessToken);
//        System.out.println(githubUser.getId());
//        System.out.println(githubUser.getName());
//        System.out.println(githubUser.getBio());
        //cookie、session使用
        if(githubUser != null && githubUser.getId() != null){
            //存入h2数据库
            User user = new User();
            user.setName(githubUser.getName());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setBio(githubUser.getBio());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token", token));
//            List<User> userList = userMapper.qryUser();
//            System.out.println(userList);
//            for (User user1 : userList) {
//                System.out.println(user1.getName());
//            }
            //登陆成功,写cookie、session;  当服务器重启时,登录状态消失,解决方法：数据库
            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        } else {
           //登录失败,重新登录
            return "redirect:/";
        }
    }

    /**
     * 退出登录
     */
    @GetMapping("/logOut")
    public String logOut(HttpServletRequest request,HttpServletResponse response) {
        //1.移除浏览器的session
        request.getSession().removeAttribute("user");
        //2.移除cookie
        //2.1重新建立一个同名立即删除类型的cookie
        Cookie token = new Cookie("token", null);
        //2.2立即删除型
        token.setMaxAge(0);
        response.addCookie(token);
        return "redirect:/";
    }
}
