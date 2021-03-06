package com.ritian.community.interceptor;

import com.ritian.community.mapper.UserMapper;
import com.ritian.community.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: wangth_oup
 * @date: 2020-04-20 16:13
 * @description:
 **/
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("----------进入拦截器----------");
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length !=0) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    System.out.println("---------获取cookie名字为\"token\"的值："+token+"-------");
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    //使用mybatis逆向工程生成的mapper
//                    UserExample example = new UserExample();
//                    UserExample.Criteria criteria = example.createCriteria();
//                    criteria.andTokenEqualTo(token);
//                    List<User> users = userMapper.selectByExample(example);
//                    if(users.size() != 0){
//                        request.getSession().setAttribute("user", users.get(0));
//                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
