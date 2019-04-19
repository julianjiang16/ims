package com.wjh.ims.biz.role;

import com.wjh.ims.dto.vo.role.RoleVO;

import java.util.List;

/**
 * Copyright (C), 2015-2018
 * FileName: RoleService
 * Author:   jcj
 * Date:     2018/9/20 9:46
 * Description: 角色服务接口
 */
public interface RoleService {
    /**
     * 功能描述: <br> 分页获取角色列表
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/20 9:48
     */
    List<RoleVO> queryRoles(int start, int rows);

    /**
     * 功能描述: <br> 添加角色
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/20 10:02
     */
    void addRole(RoleVO roleVO);

    /**
     * 功能描述: <br> 添加、编辑角色菜单
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/20 10:18
     */
    void editRoleMenu(String roleId, List<String> menuIds);

}
