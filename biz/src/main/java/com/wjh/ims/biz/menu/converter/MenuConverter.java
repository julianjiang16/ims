package com.wjh.ims.biz.menu.converter;

import com.google.common.collect.Lists;
import com.wjh.ims.dal.model.menu.Menu;
import com.wjh.ims.dto.vo.menu.MenuVO;

import java.util.List;

/**
 * Copyright (C), 2015-2018
 * FileName: MenuConverter
 * Author:   jcj
 * Date:     2018/9/16 13:21
 * Description: 菜单模型转换器
 */
public class MenuConverter {

    /**
     * 功能描述: <br> vo to do
     * @since: 1.0.0
     * @Author:jcj
     * @Date: 2018/9/16 13:22
     */
    public static Menu convertVOToDO(MenuVO menuVO) {
        Menu menu = new Menu();
        menu.setId(menuVO.getId());
        menu.setName(menuVO.getName());
        menu.setLeaf(menuVO.getLeaf());
        menu.setNodeId(menuVO.getNodeId());
        menu.setParentId(menuVO.getParentId());
        menu.setQtitle(menuVO.getQtitle());
        return menu;
    }

    /**
     * 功能描述: <br> vos to dos
     * @since: 1.0.0
     * @Author:jcj
     * @Date: 2018/9/16 13:22
     */
    public static List<Menu> convertListVOToListDO(List<MenuVO> menuVOs) {
        List<Menu> menus = Lists.newArrayList();
        for (MenuVO menuVO : menuVOs) {
            menus.add(convertVOToDO(menuVO));
        }
        return menus;
    }

    /**
     * 功能描述: <br> vo to do
     * @since: 1.0.0
     * @Author:jcj
     * @Date: 2018/9/16 13:22
     */
    public static MenuVO convertDOToVO(Menu menu,boolean flag) {
        MenuVO menuVO = new MenuVO();
        menuVO.setId(menu.getId());
        menuVO.setName(menu.getName());
        menuVO.setLeaf(menu.getLeaf());
        menuVO.setNodeId(menu.getNodeId());
        menuVO.setParentId(menu.getParentId());
        menuVO.setQtitle(menu.getQtitle());
        menuVO.setLevel(menu.getLevel());
        menuVO.setIcon(menu.getIcon());
        if (flag){
            menuVO.setChecked(false);
        }
        return menuVO;
    }

    /**
     * 功能描述: <br> vos to dos
     * @since: 1.0.0
     * @Author:jcj
     * @Date: 2018/9/16 13:22
     */
    public static List<MenuVO> convertListDOToListVO(List<Menu> menus,boolean flag) {
        List<MenuVO> menuVOs = Lists.newArrayList();
        for (Menu menu : menus) {
            menuVOs.add(convertDOToVO(menu,flag));
        }
        return menuVOs;
    }
}
