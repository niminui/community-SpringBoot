package com.nmh.community_nmh.mapper;

import com.nmh.community_nmh.dto.QuestionDTO;
import com.nmh.community_nmh.model.Question;
import com.nmh.community_nmh.model.User;
import org.apache.ibatis.annotations.*;

import java.awt.*;
import java.util.List;

/**
 * @author niminui
 * @date 2020/3/6 12:22
 */
@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "creator",column = "creator"),
            @Result(property = "title",column = "title"),
            @Result(property = "description",column = "description"),
            @Result(property = "tag",column = "tag"),
            @Result(property = "gmtCreate",column = "gmt_create"),
            @Result(property = "gmtModified",column = "gmt_modified"),
            @Result(property = "viewCount",column = "view_count"),
            @Result(property = "commentCount",column = "comment_count"),
            @Result(property = "likeCount",column = "like_count"),
            @Result(property = "user",column = "creator",
                    one = @One(select = "com.nmh.community_nmh.mapper.UserMapper.list")),
    })
    List<Question> list();

}
