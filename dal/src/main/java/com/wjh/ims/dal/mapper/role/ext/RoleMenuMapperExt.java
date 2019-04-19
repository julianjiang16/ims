package com.wjh.ims.dal.mapper.role.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuMapperExt {
    
    void insertRoleMenu(@Param("roleId") String roleId, @Param("menuIds") List<String> menuIds);
}