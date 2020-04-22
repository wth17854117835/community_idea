package com.ritian.community.mapper;

import com.ritian.community.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: wangth_oup
 * @date: 2019-11-03 14:23
 * @description:
 **/
@Mapper
public interface UserMapper {

    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,bio,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{bio},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user")
    List<User> qryUser();

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer creator);

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set avatar_url = #{avatarUrl},name = #{name},token = #{token},gmt_modified = #{gmtModified} where id = #{id}")
    void update(User user);
}
