package com.wjh.ims.biz.role.impl;

import com.wjh.ims.biz.BaseServiceImpl;
import com.wjh.ims.biz.role.RoleService;
import com.wjh.ims.biz.role.converter.RoleConverter;
import com.wjh.ims.dal.mapper.role.RoleMapper;
import com.wjh.ims.dal.mapper.role.RoleMenuMapper;
import com.wjh.ims.dal.mapper.role.ext.RoleMenuMapperExt;
import com.wjh.ims.dal.model.role.RoleExample;
import com.wjh.ims.dal.model.role.RoleMenuExample;
import com.wjh.ims.dto.vo.role.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright (C), 2015-2018
 * FileName: RoleServiceImpl
 * Author:   jcj
 * Date:     2018/9/20 9:49
 * Description: 角色服务实现类
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService
{
    @Autowired
    RoleMapper roleMapper;
    
    @Autowired
    RoleMenuMapper roleMenuMapper;
    
    @Autowired
    RoleMenuMapperExt roleMenuMapperExt;
    
    public List<RoleVO> queryRoles(int start, int rows)
    {
        RoleExample example = new RoleExample();
        example.setStart(start);
        example.setRows(rows);
        example.setOrderByClause("gmt_create desc");
        
        return RoleConverter.convertListDOToListVO(roleMapper.selectByExample(example));
    }
    
    public void addRole(RoleVO roleVO)
    {
        roleMapper.insertSelective(RoleConverter.convertVOToDO(roleVO));
    }
    
    @Transactional
    public void editRoleMenu(String roleId, List<String> menuIds)
    {
        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleMenuMapper.deleteByExample(example);
        
        roleMenuMapperExt.insertRoleMenu(roleId, menuIds);
    }

}
