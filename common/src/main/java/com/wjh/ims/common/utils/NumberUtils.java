package com.wjh.ims.common.utils;

import com.wjh.ims.common.enums.NoTypeEnum;
import org.apache.commons.lang.StringUtils;

/**
 * 数字工具类
 */
public class NumberUtils {

    private static final String DEFAULT_GOODS_TYPE_NO = "001";

    private static final String DEFAULT_GOODS_NO = "000001";

    private static final String DEFAULT_SUPPLIER_OR_CUSTOMER_NO = "0001";

    private static final String DEFAULT_COMMON_NO = "0001";


    public static String getNumStr(String num, int len, NoTypeEnum type) throws Exception {

        if (StringUtils.isEmpty(num)) {
            switch (type) {
                case GOODS_NO:
                    return DEFAULT_GOODS_NO;
                case CUSTOMER_NO:
                    return DEFAULT_SUPPLIER_OR_CUSTOMER_NO;
                case SUPPLIER_NO:
                    return DEFAULT_SUPPLIER_OR_CUSTOMER_NO;
                case GOODS_TYPE_NO:
                    return DEFAULT_GOODS_TYPE_NO;
                case DEFAULT_NO:
                    return DEFAULT_COMMON_NO;
                default:
                    return "";
            }
        }
        int number = Integer.valueOf(num);
        number++;
        if (number > 999 || number < 0) {
            throw new Exception("分类数大于999，参数异常:" + num);
        }
        String result = "" + number;
        int diff = len - result.length();
        if (diff > 0) {
            for (int i = 0; i < diff; i++) {
                result = "0" + result;
            }
        }
        return result;
    }
}
