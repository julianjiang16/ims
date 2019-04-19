package com.wjh.ims.dto.vo.role;

/**
 * Copyright (C), 2015-2018
 * FileName: RoleMenuVO
 * Author:   jcj
 * Date:     2018/9/20 10:39
 * Description: 角色菜单vo
 */
public class RoleMenuVO {

    /** 角色id */
    private String roleId;

    /** 菜单id */
    private String menuId;

    public RoleMenuVO() {
    }

    public RoleMenuVO(String roleId, String menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
