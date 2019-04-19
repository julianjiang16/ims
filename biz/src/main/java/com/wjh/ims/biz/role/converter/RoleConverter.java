package com.wjh.ims.biz.role.converter;

import com.google.common.collect.Lists;
import com.wjh.ims.common.utils.IDMaker;
import com.wjh.ims.dal.model.role.Role;
import com.wjh.ims.dto.vo.role.RoleVO;

import java.util.Date;
import java.util.List;

/**
 * Copyright (C), 2015-2018
 * FileName: RoleConverter
 * Author:   jcj
 * Date:     2018/9/20 9:51
 * Description: 角色转换类
 */
public class RoleConverter {

    /**
     * 功能描述: <br> vo to do
     * @since: 1.0.0
     * @Author:jcj
     * @Date: 2018/9/16 13:22
     */
    public static Role convertVOToDO(RoleVO roleVO) {
        Role role = new Role();
        role.setId(IDMaker.get().nextId());
        role.setName(roleVO.getName());
        role.setGmtCreate(new Date());
        return role;
    }

    /**
     * 功能描述: <br> vos to dos
     * @since: 1.0.0
     * @Author:jcj
     * @Date: 2018/9/16 13:22
     */
    public static List<Role> convertListVOToListDO(List<RoleVO> roleVOS) {
        List<Role> roles = Lists.newArrayList();
        for (RoleVO roleVO : roleVOS) {
            roles.add(convertVOToDO(roleVO));
        }
        return roles;
    }

    /**
     * 功能描述: <br> vo to do
     * @since: 1.0.0
     * @Author:jcj
     * @Date: 2018/9/16 13:22
     */
    public static RoleVO convertDOToVO(Role role) {
        RoleVO roleVO = new RoleVO();
        roleVO.setId(role.getId());
        roleVO.setName(role.getName());
        roleVO.setGmtCreate(new Date());
        return roleVO;
    }

    /**
     * 功能描述: <br> vos to dos
     * @since: 1.0.0
     * @Author:jcj
     * @Date: 2018/9/16 13:22
     */
    public static List<RoleVO> convertListDOToListVO(List<Role> roles) {
        List<RoleVO> roleVOS = Lists.newArrayList();
        for (Role role : roles) {
            roleVOS.add(convertDOToVO(role));
        }
        return roleVOS;
    }
}
