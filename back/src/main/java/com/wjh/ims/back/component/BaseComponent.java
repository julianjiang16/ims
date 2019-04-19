package com.wjh.ims.back.component;

import com.wjh.ims.back.Constant;
import com.wjh.ims.dto.vo.user.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Copyright (C), 2015-2018
 * FileName: BaseComponent
 * Author:   jcj
 * Date:     2018/9/21 15:59
 * Description: 基础组件
 */
public class BaseComponent {

    /**
     * 功能描述: <br> 获取session user对象
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/21 16:02
     */
    protected UserVO getSessionUser(HttpServletRequest request)
    {
        return (UserVO)request.getSession().getAttribute(Constant.COOKIE_USER_KEY);
    }

    protected void setResponseHeader(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        fileName = new String(fileName.getBytes(),"ISO8859-1");
        response.setContentType("application/octet-stream;charset=ISO8859-1");
        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
    }
}
