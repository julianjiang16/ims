package com.wjh.ims.common.utils;

import com.wjh.ims.common.enums.ImsErrorEnum;
import com.wjh.ims.common.exception.ImsException;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Copyright (C), 2015-2018
 * FileName: DateUtils
 * Author:   jcj
 * Date:     2018/10/12 10:29
 * Description: 日期工具类
 */
public class DateUtils {

    private static final String FULL_DATE_FORMAT = "yyyy-MM-ddhh:mm:ss";
    
    private static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";

    private static final String SHORT_DATE_FORMAT_2 = "yyyy/MM/dd";

    /**
     * 功能描述: <br> 格式化短日期str to 日期类型
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/10/12 10:30
     */
    public static Date formatShortStr2Date(String shortDate) throws ImsException {
        if (StringUtils.isEmpty(shortDate)){
            return null;
        }
        SimpleDateFormat sdf=new SimpleDateFormat(SHORT_DATE_FORMAT);

        try {
            return sdf.parse(shortDate);
        } catch (ParseException e) {
            throw new ImsException(ImsErrorEnum.SHORT_DATE_STRING_FORMAT_ERROR);
        }
    }

    public static String formatDate2ShortStr(Date date){
        if (date==null){
            return null;
        }
        SimpleDateFormat sdf=new SimpleDateFormat(SHORT_DATE_FORMAT);
        return sdf.format(date);
    }


    public static String formatDate3ShortStr(Date date){
        if (date==null){
            return null;
        }
        SimpleDateFormat sdf=new SimpleDateFormat(SHORT_DATE_FORMAT_2);
        return sdf.format(date);
    }

    /**
     * 功能描述: <br> 将yyyy-MM-dd ,hh:mm:ss 字符串拼接并转换为date
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/10/24 15:45
     */
    public static Date formatDayAndTime2Date(String day,String time) throws ImsException {
        String dateStr=day+time;
        SimpleDateFormat sdf=new SimpleDateFormat(FULL_DATE_FORMAT);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
           throw new ImsException(ImsErrorEnum.DATE_FORMAT_ERROR,"格式的日期字符串为："+dateStr);
        }
    }

    /**
     * 功能描述: <br> 计算年龄
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/10/25 10:31
     */
    public static int calculateAge(Date birthday)
    {
        if (birthday == null)
        {
            return 0;
        }
        int age;
        try
        {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());
            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);
            if (birth.after(now))
            {
                age = 0;
            }
            else
            {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR))
                {
                    age += 1;
                }
            }
            return age;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
}
