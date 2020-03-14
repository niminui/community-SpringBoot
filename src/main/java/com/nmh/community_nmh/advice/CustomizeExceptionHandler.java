package com.nmh.community_nmh.advice;

import com.alibaba.fastjson.JSON;
import com.nmh.community_nmh.dto.ResultDTO;
import com.nmh.community_nmh.exception.CustomizeErrorCode;
import com.nmh.community_nmh.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author niminui
 * @date 2020/3/13 11:53
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    Object handle(HttpServletRequest request, HttpServletResponse response,
                  Throwable ex, Model model) {
        String contentType = request.getContentType();
        if("application/json".equals(contentType)) {
            //返回json
            ResultDTO resultDTO = null;
            if(ex instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf((CustomizeException)ex);
            } else {
                resultDTO =  ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setCharacterEncoding("UTF-8");
                response.setStatus(200);
                response.setContentType("application/json");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return null;
        } else {
            //错误页面跳转
            if(ex instanceof CustomizeException) {
                model.addAttribute("message",ex.getMessage());
            } else {
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }

    }
}
