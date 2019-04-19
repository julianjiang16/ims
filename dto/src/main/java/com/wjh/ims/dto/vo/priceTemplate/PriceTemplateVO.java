package com.wjh.ims.dto.vo.priceTemplate;

import java.util.Date;

public class PriceTemplateVO {
    
    private String id;

    
    private String no;

    
    private String name;

    
    private String type;


    private String remarks;


    private Date gmtCreate;

    
    private Date gmtModified;


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    
    public String getNo() {
        return no;
    }

    
    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    
    public String getType() {
        return type;
    }

    
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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