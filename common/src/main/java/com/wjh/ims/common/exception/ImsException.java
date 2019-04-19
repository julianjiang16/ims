package com.wjh.ims.common.exception;

import com.wjh.ims.common.enums.ImsErrorEnum;

/**
 * Copyright (C), 2015-2019
 * FileName: ImsException
 * Author:   jcj
 * Date:     2019/3/22 10:46
 * Description:
 */
public class ImsException extends Exception {

    /** 异常枚举类 */
    private final ImsErrorEnum errorEnum;

    /** 异常信息 */
    private final String errorMsg;

    /** 异常对象信息 */
    private final String errorObjInfo;

    public ImsException(ImsErrorEnum errorEnum, String errorMsg, String errorObjInfo) {
        super(errorEnum.getChinaName());
        this.errorEnum = errorEnum;
        this.errorMsg = errorMsg;
        this.errorObjInfo = errorObjInfo;
    }



    public ImsException(ImsErrorEnum errorEnum, String errorObjInfo) {
        super(errorEnum.getChinaName());
        this.errorEnum = errorEnum;
        this.errorMsg = errorEnum.getChinaName();
        this.errorObjInfo = errorObjInfo;
    }

    public ImsException(ImsErrorEnum errorEnum) {
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
