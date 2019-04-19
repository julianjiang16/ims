package com.wjh.ims.common.enums;

/**
 * Copyright (C), 2015-2019,
 * FileName: NoTypeEnum
 * Author:   jcj
 * Date:     2019/3/25 11:01
 * Description:
 */
public enum NoTypeEnum {

    GOODS_NO("01","商品编号"),

    GOODS_TYPE_NO("02","商品类型编号"),

    CUSTOMER_NO("03","客户编号"),

    SUPPLIER_NO("04","供应商编号"),

    DEFAULT_NO("05","默认编号")
    ;

    NoTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    String code;
    String desc;
}
