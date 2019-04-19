package com.wjh.ims.dto.vo.goods;

import java.util.Date;

public class GoodsTypeVO {
    
    private String id;

    
    private String name;


    private String no;

    
    private Date gmtCreate;

    
    private Date gmtModified;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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