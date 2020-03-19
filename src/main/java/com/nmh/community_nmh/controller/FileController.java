package com.nmh.community_nmh.controller;

import com.nmh.community_nmh.dto.FileDTO;
import com.nmh.community_nmh.provider.ALiYunProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * @author niminui
 * @date 2020/3/18 12:14
 */
@Controller
public class FileController {

    @Autowired
    private ALiYunProvider aLiYunProvider;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        FileDTO fileDTO = new FileDTO();
        try {
            URL fileName = aLiYunProvider.upload(file.getInputStream(),
                    file.getContentType(), file.getOriginalFilename());
            fileDTO.setSuccess(1);
            fileDTO.setUrl(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileDTO;
    }

}
