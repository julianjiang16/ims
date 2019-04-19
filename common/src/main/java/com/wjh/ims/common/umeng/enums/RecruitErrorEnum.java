package com.wjh.ims.common.umeng.enums;

import org.apache.commons.lang.StringUtils;

/**
 * Copyright (C), 2015-2018, 大胜百一网络科技
 * FileName: RecruitErrorConstant
 * Author:   jcj
 * Date:     2018/9/15 11:07
 * Description: 系统统一错误代码常量类
 */
public enum RecruitErrorEnum {

    /** 主键冲突异常 */
    DOUBLE_KEY_ERROR("20001", "DOUBLE_KEY_ERROR", "主键冲突错误", "主键冲突错误"),

    /** 不合法的整数 */
    DIGITAL_UNLAWFUL_ERROR("20002", "DIGITAL_UNLAWFUL_ERROR", "不合法的整数", "不合法的整数"),

    /** 短日期字符串格式错误 */
    SHORT_DATE_STRING_FORMAT_ERROR("20003", "SHORT_DATE_STRING_FORMAT_ERROR", "短日期字符串格式错误", "短日期字符串格式错误"),

    /** 日期格式转换异常 */
    DATE_FORMAT_ERROR("20004", "DATE_FORMAT_ERROR", "日期格式转换异常", "日期格式转换异常"),

    /** 指定订阅信息下没有订阅用户 */
    NO_SUBSCRIBERS_ERROR("20005", "NO_SUBSCRIBERS_ERROR", "指定订阅信息下没有订阅用户", "指定订阅信息下没有订阅用户"),

    /** 删除订阅消息，该订阅消息存在招聘信息 */
    REMOVE_SUB_INFO_EXIT_RECRUIT_ERROR("20006", "REMOVE_SUB_INFO_EXIT_RECRUIT_ERROR", "删除订阅消息，该订阅消息存在招聘信息", "删除订阅消息，该订阅消息存在招聘信息"),

    /** 删除消息类目，该类目下存在消息 */
    REMOVE_MSG_CATEGORY_EXIT_MSG_ERROR("20007", "REMOVE_MSG_CATEGORY_EXIT_MSG_ERROR", "删除消息类目，该类目下存在消息", "删除消息类目，该类目下存在消息"),

    /** 该订阅信息下没有订阅用户 */
    WITHOUT_SUBSCRIBE_USER_ERROR("20008", "WITHOUT_SUBSCRIBE_USER_ERROR", "该订阅信息下没有订阅用户", "该订阅信息下没有订阅用户"),

    /** 删除订阅消息，该订阅消息已被用户订阅，不能删除 */
    REMOVE_SUB_INFO_EXIT_SUB_USER_ERROR("20009", "REMOVE_SUB_INFO_EXIT_SUB_USER_ERROR", "删除订阅消息，该订阅消息已被用户订阅，不能删除", "删除订阅消息，该订阅消息已被用户订阅，不能删除"),

    /** 上传视频时，视频无发布者 */
    ATTACHMENT_NO_PUBLISHER_ERROR("20010", "ATTACHMENT_NO_PUBLISHER_ERROR", "上传视频时，视频无发布者", "上传视频时，视频无发布者"),

    /** android 推送消息填充参数错误 */
    PUSH_ANDROID_MESSAGE_PARAM_FILL_ERROR("20011", "PUSH_ANDROID_MESSAGE_PARAM_FILL_ERROR", "android 推送消息填充参数错误", "You don't need to set value for key , just set values for the sub keys in it."),

    /** android 推送消息填充参数,未知参数错误 */
    PUSH_ANDROID_MESSAGE_UNKNOWN_PARAM_ERROR("20012", "PUSH_ANDROID_MESSAGE_UNKNOWN_PARAM_ERROR", "android 推送消息填充参数,未知参数错误", "umeng push android message,fill params error,unknown key."),

    /** android 推送消息填充参数,缺少参数错误 */
    PUSH_ANDROID_MESSAGE_MISSING_PARAM_ERROR("20013", "PUSH_ANDROID_MESSAGE_MISSING_PARAM_ERROR", "android 推送消息填充参数,缺少参数错误", "umeng push android message,fill params error,missing key."),

    /** umeng消息推送失败 */
    PUSH_UMENG_MESSAGE_FAIL_ERROR("20014", "PUSH_UMENG_MESSAGE_FAIL_ERROR", "umeng消息推送失败", "umeng push message fail error."),

    /** ios 推送消息填充参数错误 */
    PUSH_IOS_MESSAGE_PARAM_FILL_ERROR("20015", "PUSH_IOS_MESSAGE_PARAM_FILL_ERROR", "ios 推送消息填充参数错误", "You don't need to set value for key , just set values for the sub keys in it."),

    /** ios 推送消息填充参数,未知参数错误 */
    PUSH_IOS_MESSAGE_UNKNOWN_PARAM_ERROR("20016", "PUSH_IOS_MESSAGE_UNKNOWN_PARAM_ERROR", "ios 推送消息填充参数,未知参数错误", "umeng push ios message,fill params error,unknown key."),;

    String code;
    String englishName;
    String chinaName;
    String description;

    RecruitErrorEnum(String code, String englishName, String chinaName, String description) {
        this.code = code;
        this.englishName = englishName;
        this.chinaName = chinaName;
        this.description = description;
    }

    /**
     * 功能描述: <br>根据code获取枚举类
     * @Author: jcj
     * @Date: 2018/9/15 11:13
     */
    public RecruitErrorEnum getByCode(String code) {
        for (RecruitErrorEnum e : RecruitErrorEnum.values()) {
            if (StringUtils.equals(code, e.code)) {
                return e;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getChinaName() {
        return chinaName;
    }

    public String getDescription() {
        return description;
    }
}
