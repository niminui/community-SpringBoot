package com.nmh.community_nmh.mapper;

import com.nmh.community_nmh.model.Question;
import com.nmh.community_nmh.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author niminui
 * @date 2020/3/5 22:30
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where id = #{id}")
    User getUser(@Param("id") Integer id);

    @Insert("update user set name = #{name}, token = #{token}, gmt_modified = #{gmtModified}, avatar_url = #{avatarUrl} where id = #{id}")
    void update(User dbUser);

    @Select("select * from user where account_ID = #{accountId}")
    User getByAccountId(String accountId);
}
