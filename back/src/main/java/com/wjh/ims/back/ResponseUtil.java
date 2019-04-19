package com.wjh.ims.back;

import org.apache.commons.lang.StringUtils;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2015-2018
 * FileName: ResponseUtil
 * Author:   jcj
 * Date:     2018/9/18 17:22
 * Description: 响应类
 */
public class ResponseUtil {
    public static Response success(String msg){
        return Response.ok(detailMsg(msg,true)).build();
    }
    public static Response failure(String msg){
        if (StringUtils.isEmpty(msg)){
            msg="服务器异常";
        }
        return Response.ok(detailMsg(msg,false)).build();
    }
    public static Map detailMsg(String msg,boolean flag){
        Map map=new HashMap();
        map.put("success",flag);
        map.put("msg",msg);
        return map;
    }
    public static Response data(Object source,int count){
        Map map=new HashMap();
        map.put("data",source);
        map.put("count",count);
        return Response.ok(map).build();
    }
}
