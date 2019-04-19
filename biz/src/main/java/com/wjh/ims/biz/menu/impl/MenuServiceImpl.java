package com.wjh.ims.biz.menu.impl;

import com.google.common.collect.Lists;
import com.wjh.ims.biz.BaseServiceImpl;
import com.wjh.ims.biz.menu.MenuService;
import com.wjh.ims.biz.menu.converter.MenuConverter;
import com.wjh.ims.dal.mapper.menu.MenuMapper;
import com.wjh.ims.dal.mapper.menu.ext.MenuMapperExt;
import com.wjh.ims.dal.mapper.role.RoleMenuMapper;
import com.wjh.ims.dal.model.menu.Menu;
import com.wjh.ims.dal.model.menu.MenuExample;
import com.wjh.ims.dal.model.role.RoleMenu;
import com.wjh.ims.dal.model.role.RoleMenuExample;
import com.wjh.ims.dto.vo.menu.MenuVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * Copyright (C), 2015-2018
 * FileName: MenuServiceImpl
 * Author:   jcj
 * Date:     2018/9/18 17:37
 * Description: 菜单服务实现类
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;
    
    @Autowired
    RoleMenuMapper roleMenuMapper;
    
    @Autowired
    MenuMapperExt menuMapperExt;

    // N级菜单
    public List<MenuVO> loadMenu(String roleId) {
        // 加载所有菜单
        List<MenuVO> resource = MenuConverter.convertListDOToListVO(menuMapperExt.selectRoleMenu(roleId), false);
        // 返回分级菜单
        return classifyMenu(resource);
    }


    public void edit(MenuVO menuVO) {
        Menu menu = mf.map(menuVO, Menu.class);
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    public void add(MenuVO menu) {
        Menu menu1 = mf.map(menu, Menu.class);
        menuMapper.insertSelective(menu1);
    }

    public void remove(String id) {
        menuMapper.deleteByPrimaryKey(id);
    }

    public List<MenuVO> loadRoleMenu(String roleId)
    {
        // 1.加载所有的菜单 并填充checked字段
        List<MenuVO> source = loadAllWithoutClassify(true);
        // 2.修改选中checked状态
        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<RoleMenu> roleMenus = roleMenuMapper.selectByExample(example);
        // 3.1 判断角色关联关系
        if (CollectionUtils.isEmpty(roleMenus))
        {
            return classifyMenu(source);
        }
        // 3.2 分级菜单
        for (RoleMenu rm : roleMenus)
        {
            for (MenuVO menuVO : source)
            {
                if (StringUtils.equals(rm.getMenuId(), menuVO.getId()))
                {
                    menuVO.setChecked(true);
                    break;
                }
            }
        }
        return classifyMenu(source);
    }


    /**
     * 功能描述: <br> 加载没有分级的所有菜单
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/20 17:20
     */
    protected List<MenuVO> loadAllWithoutClassify(boolean flag){
        MenuExample example = new MenuExample();
        List<MenuVO> resource = MenuConverter.convertListDOToListVO(menuMapper.selectByExample(example),flag);
        return resource;
    }

    /**
     * 功能描述: <br> 分级菜单
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/20 17:22
     */
    protected List<MenuVO> classifyMenu(List<MenuVO> resource)
    {
        List<MenuVO> result = Lists.newArrayList();
        for (int i = 0; i < resource.size(); i++)
        {
            MenuVO parent = resource.get(i);
            parent.setText(parent.getName());
            for (int j = 0; j < resource.size(); j++)
            {
                MenuVO children = resource.get(j);
                if (children.getParentId().equals(parent.getId()) && !parent.getLeaf())
                {
                    parent.addChildren(children);
                }
            }
            if (parent.getParentId().equals("0"))
                result.add(parent);
        }
        return result;
    }
}
