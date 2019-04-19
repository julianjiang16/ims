package com.wjh.ims.common.utils;

import com.wjh.ims.common.constant.ImsConstant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright (C), 2015-2018
 * FileName: PatternUtils
 * Author:   jcj
 * Date:     2018/10/16 14:16
 * Description: 正则工具类
 */
public class PatternUtils {

    private static final String CONTENT_TYPE_OF_FILE_TYPE_REGEX="filename=([\\s\\S]*)\\\\";

    /**
     * 功能描述: <br> 通过contentType 获取文件类型
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/10/16 14:18
     */
    public static String getFileTypeFromContentType(String source)
    {
        Pattern p = Pattern.compile(CONTENT_TYPE_OF_FILE_TYPE_REGEX);
        Matcher m = p.matcher(source);
        String result = "";
        while (m.find())
        {
            result = m.group(1);
        }
        if (result.contains(ImsConstant.POINT_SEPARATOR)){
            result = result.substring(result.lastIndexOf(ImsConstant.POINT_SEPARATOR));
        }
        return getSuffix(result);
    }

    /**
     * 功能描述: <br> 获取后缀
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/10/16 14:20
     */
    public static String getSuffix(String name){
        if (name.contains(ImsConstant.POINT_SEPARATOR)){
            return name.substring(name.indexOf(ImsConstant.POINT_SEPARATOR)+1);
        }
        return null;
    }
}
