package com.wjh.ims.dto.vo.role;

import java.util.Date;

public class RoleVO {
    
    private String id;

    
    private String name;

    
    private Date gmtCreate;

    
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
}