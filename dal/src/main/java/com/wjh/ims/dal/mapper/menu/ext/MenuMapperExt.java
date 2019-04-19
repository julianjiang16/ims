package com.wjh.ims.dal.mapper.menu.ext;

import com.wjh.ims.dal.model.menu.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapperExt {
   List<Menu> selectRoleMenu(@Param("roleId") String id);
}