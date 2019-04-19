package com.wjh.ims.biz.menu;


import com.wjh.ims.dto.vo.menu.MenuVO;

import java.util.List;

/**
 * Copyright (C), 2015-2018
 * FileName: MenuService
 * Author:   jcj
 * Date:     2018/9/18 17:37
 * Description: 菜单服务
 */
public interface MenuService {
    /**
     * 功能描述: <br>加载菜单
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/18 17:37
     */
    List<MenuVO> loadMenu(String roleId);

    /**
     * 功能描述: <br> 编辑菜单
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/18 17:37
     */
    void edit(MenuVO menu);
    /**
     * 功能描述: <br> 添加菜单
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/18 17:37
     */
    void add(MenuVO menu);
    /**
     * 功能描述: <br> 删除菜单
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/18 17:37
     */
    void remove(String id);

    /**
     * 功能描述: <br> 获取指定角色的菜单列表
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/20 10:41
     */
    List<MenuVO> loadRoleMenu(String roleId);
}
