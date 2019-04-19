package com.wjh.ims.common.enums;

/**
 * Copyright (C), 2015-2019,
 * FileName: GoodsTablesFieldEnum
 * Author:   jcj
 * Date:     2019/3/25 15:01
 * Description: 商品表字段
 */
public enum GoodsTablesFieldEnum {

    ID("01", "id", "getId", "id"),
    GOODS_NO("02", "goods_no", "getGoodsNo", "商品编号"),
    GOODS_NAME("03", "goods_name", "getGoodsName", "商品名称"),
    TYPE_NO("04", "type_no", "getTypeNo", "类型编号"),
    SPELL("05", "spell", "getSpell", "拼音助记码"),
    SPEC("06", "spec", "getSpec", "简称"),
    AUX_UNIT_FIRST("07", "aux_unit_first", "getAuxUnitFirst", "辅助单位1"),
    AUX_VAL_FIRST("08", "aux_val_first", "getAuxValFirst", "辅助单位1的换算值"),
    AUX_UNIT_SECOND("09", "aux_unit_second", "getAuxUnitSecond", "辅助单位2"),
    AUX_VAL_SECOND("10", "aux_val_second", "getAuxValSecond", "辅助单位2的换算值"),
    NUM("11", "num", "getNum", "库存数量"),
    EFFECTIVE_DAYS("12", "effective_days", "getEffectiveDays", "有效天数"),
    REMARKS("13", "remarks", "getRemarks", "备注"),
    GMT_CREATE("14", "gmt_create", "getGmtCreate", "创建时间");

    String code;
    String field;
    String method;
    String desc;

    GoodsTablesFieldEnum(String code, String field, String method, String desc) {
        this.code = code;
        this.field = field;
        this.method = method;
        this.desc = desc;
    }

    public static GoodsTablesFieldEnum getByCode(String code){
        for (GoodsTablesFieldEnum goodsTablesFieldEnum:GoodsTablesFieldEnum.values()){
            if (goodsTablesFieldEnum.getCode().equals(code)){
                return goodsTablesFieldEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
