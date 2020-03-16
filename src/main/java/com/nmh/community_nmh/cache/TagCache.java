package com.nmh.community_nmh.cache;

import com.nmh.community_nmh.dto.TagDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author niminui
 * @date 2020/3/16 22:46
 */
public class TagCache {

    public static List<TagDTO> get() {
        List<TagDTO> tagDTOs = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js","php","css","html","html5","java","node.js","python","c++","c","golang","objective-c","typescript","shell","swift","c#","sass","ruby","bash","less","asp.net","lua","scala","coffeescript","actionscript","rust","erlang","perl"));
        tagDTOs.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("laravel","spring","express","django"," flask","yii","ruby-on-rails","tornado","koa","struts"));
        tagDTOs.add(framework);

        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("liunx","nginx","docker","apache"," ubuntu","centos","缓存","tomcat","负载均衡","unix","hadoop","windows-server"));
        tagDTOs.add(server);

        TagDTO database = new TagDTO();
        database.setCategoryName("数据库");
        database.setTags(Arrays.asList("mysql","redis","mongodb","sql","oracle","nosql memcached","sqlserver","postgresql","sqlite"));
        tagDTOs.add(database);

        TagDTO tool = new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git","github","macos","visual-studio-code","windows vim","sublime-text","intellij-idea","eclipse","phpstorm","webstorm","编辑器","svn","visual-studio","pycharm","emacs"));
        tagDTOs.add(tool);
        return tagDTOs;
    }

    public static String filterInvalid(String tags) {
        String[] split = tags.split(",");
        List<TagDTO> tagDTOs = get();
        List<String> tagList = tagDTOs.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        return Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
    }

}
