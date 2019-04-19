package com.wjh.ims.common.utils;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * Copyright (C), 2015-2019
 * FileName: StringUtils
 * Author:   jcj
 * Date:     2019/3/25 16:02
 * Description:
 */
public class StringUtils {

    public static List<String> splitStrByPattern(String source,String pattern){
        List<String> result= Lists.newArrayList();
        Collections.addAll(result,source.split(pattern));
        return result;
    }
}
