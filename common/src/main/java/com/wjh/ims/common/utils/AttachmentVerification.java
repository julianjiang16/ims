package com.wjh.ims.common.utils;

import java.util.regex.Pattern;

/**
 * Copyright (C), 2015-2018
 * FileName: AttachmentVerification
 * Author:   jcj
 * Date:     2018/11/19 11:08
 * Description: 附件验证
 */
public class AttachmentVerification {

    public static boolean imgLocation(String fileName){
        fileName = fileName.toLowerCase();
        String reg = "(mp4|flv|avi|rm|rmvb|wmv)";
        Pattern p = Pattern.compile(reg);
        boolean boo = p.matcher(fileName).find();
        return boo;
    }
}
