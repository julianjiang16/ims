package com.wjh.ims.dal.model.user.ext;

import com.wjh.ims.dal.model.user.User;

/**
 * Copyright (C), 2015-2018
 * FileName: UserExt
 * Author:   jcj
 * Date:     2018/9/21 15:10
 * Description: user do 扩展
 */
public class UserExt extends User {
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
