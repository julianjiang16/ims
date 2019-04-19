package com.wjh.ims.dto.vo.goods;

import java.util.Date;

public class GoodsVO {
    
    private String id;

    
    private String goodsNo;


    private String spell;


    private String typeNo;

    
    private String goodsName;

    
    private String spec;

    
    private String auxUnitFirst;

    
    private String auxUnitSecond;

    
    private Integer auxValFirst;

    
    private Integer auxValSecond;

    
    private Integer num;

    
    private Integer effectiveDays;

    
    private String remarks;

    
    private Date gmtCreate;

    
    private Date gmtModified;


    public String getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo;
    }

    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    
    public String getGoodsNo() {
        return goodsNo;
    }

    
    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo == null ? null : goodsNo.trim();
    }

    
    public String getSpell() {
        return spell;
    }

    
    public void setSpell(String spell) {
        this.spell = spell == null ? null : spell.trim();
    }

    
    public String getGoodsName() {
        return goodsName;
    }

    
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    
    public String getSpec() {
        return spec;
    }

    
    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    
    public String getAuxUnitFirst() {
        return auxUnitFirst;
    }

    
    public void setAuxUnitFirst(String auxUnitFirst) {
        this.auxUnitFirst = auxUnitFirst == null ? null : auxUnitFirst.trim();
    }

    
    public String getAuxUnitSecond() {
        return auxUnitSecond;
    }

    
    public void setAuxUnitSecond(String auxUnitSecond) {
        this.auxUnitSecond = auxUnitSecond == null ? null : auxUnitSecond.trim();
    }

    
    public Integer getAuxValFirst() {
        return auxValFirst;
    }

    
    public void setAuxValFirst(Integer auxValFirst) {
        this.auxValFirst = auxValFirst;
    }

    
    public Integer getAuxValSecond() {
        return auxValSecond;
    }

    
    public void setAuxValSecond(Integer auxValSecond) {
        this.auxValSecond = auxValSecond;
    }

    
    public Integer getNum() {
        return num;
    }

    
    public void setNum(Integer num) {
        this.num = num;
    }

    
    public Integer getEffectiveDays() {
        return effectiveDays;
    }

    
    public void setEffectiveDays(Integer effectiveDays) {
        this.effectiveDays = effectiveDays;
    }

    
    public String getRemarks() {
        return remarks;
    }

    
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    
    public Date getGmtCreate() {
        return gmtCreate;
    }

    
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    
    public Date getGmtModified() {
        return gmtModified;
    }

    
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}