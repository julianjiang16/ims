package com.wjh.ims.back.component.role;

import com.wjh.ims.back.ResponseUtil;
import com.wjh.ims.biz.role.RoleService;
import com.wjh.ims.common.constant.ImsConstant;
import com.wjh.ims.dto.vo.role.RoleMenuVO;
import com.wjh.ims.dto.vo.role.RoleVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2015-2018
 * FileName: RoleComponent
 * Author:   jcj
 * Date:     2018/9/20 9:15
 * Description: 角色管理
 */
@Component
@Path("/role/")
public class RoleComponent {

    /** 角色服务 */
    @Autowired
    RoleService roleService;

    /**
     * 功能描述: <br> 获取roles列表
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/20 9:44
     */
    @GET
    @Path("roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response role(@QueryParam("start") int start,@QueryParam("rows") int rows){
        List<RoleVO> roleVOS=roleService.queryRoles(start, rows);
        // todo jcj 角色不多提供分页功能，没实现
        return ResponseUtil.data(roleVOS,roleVOS.size());
    }


    /**
     * 功能描述: <br> 添加角色
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/20 10:30
     */
    @POST
    @Path("roles")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response role(RoleVO roleVO){
        try {
            roleService.addRole(roleVO);
            return ResponseUtil.success("添加成功");
        }catch (Exception e){
            return  ResponseUtil.success("添加失败，请查看日志:"+e.getMessage());
        }
    }

    /**
     * 功能描述: <br> 添加、编辑角色菜单
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/20 10:49
     */
    @POST
    @Path("roleMenu")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response roleMenu(RoleMenuVO roleMenuVO)
    {
        try
        {
            if (roleMenuVO != null && !StringUtils.isEmpty(roleMenuVO.getMenuId())
                && !StringUtils.isEmpty(roleMenuVO.getRoleId()))
            {
                String[] menuIds = roleMenuVO.getMenuId().split(ImsConstant.DOLLAR_SEPARATOR_TRANSFERENCE);
                roleService.editRoleMenu(roleMenuVO.getRoleId(), Arrays.asList(menuIds));
            }
            return ResponseUtil.success("处理成功");

        }
        catch (Exception e)
        {
            return ResponseUtil.failure("处理失败，请查看日志:"+e.getMessage());
        }

    }
}
