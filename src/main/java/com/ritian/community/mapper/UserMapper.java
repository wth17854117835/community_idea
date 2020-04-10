package com.ritian.community.mapper;

import com.ritian.community.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: wangth_oup
 * @date: 2019-11-03 14:23
 * @description:
 **/
@Mapper
public interface UserMapper {

    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Select("select * from user")
    List<User> qryUser();

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

}
