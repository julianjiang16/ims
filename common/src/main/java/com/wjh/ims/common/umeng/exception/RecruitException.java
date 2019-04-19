package com.wjh.ims.common.umeng.exception;


import com.wjh.ims.common.umeng.enums.RecruitErrorEnum;

/**
 * Copyright (C), 2015-2018, 大胜百一网络科技
 * FileName: RecruitException
 * Author:   jcj
 * Date:     2018/9/15 11:05
 * Description: 系统统一异常类
 */
public class RecruitException extends Exception {

    /** 异常枚举类 */
    private final RecruitErrorEnum errorEnum;

    /** 异常信息 */
    private final String errorMsg;

    /** 异常对象信息 */
    private final String errorObjInfo;

    public RecruitException(RecruitErrorEnum errorEnum, String errorMsg, String errorObjInfo) {
        super(errorEnum.getChinaName());
        this.errorEnum = errorEnum;
        this.errorMsg = errorMsg;
        this.errorObjInfo = errorObjInfo;
    }



    public RecruitException(RecruitErrorEnum errorEnum, String errorObjInfo) {
        super(errorEnum.getChinaName());
        this.errorEnum = errorEnum;
        this.errorMsg = errorEnum.getChinaName();
        this.errorObjInfo = errorObjInfo;
    }

    public RecruitException(RecruitErrorEnum errorEnum) {
        super();
        this.errorEnum = errorEnum;
        this.errorMsg = errorEnum.getChinaName();
        this.errorObjInfo = errorEnum.getDescription();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getErrorObjInfo() {
        return errorObjInfo;
    }
}
