package com.wjh.ims.back.component.menu;

import com.wjh.ims.back.ResponseUtil;
import com.wjh.ims.back.component.BaseComponent;
import com.wjh.ims.biz.menu.MenuService;
import com.wjh.ims.common.utils.IDMaker;
import com.wjh.ims.dto.vo.menu.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Copyright (C), 2015-2018
 * FileName: MenuComponent
 * Author:   jcj
 * Date:     2018/9/18 17:22
 * Description: 菜单控制器
 */
@Controller
@Path("/menu/")
public class MenuComponent extends BaseComponent {
    @Autowired
    MenuService menuService;

    /**
     * 功能描述: <br>用户菜单
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/18 17:31
     */
    @GET
    @Path("menu")
    @Produces(MediaType.APPLICATION_JSON)
    public List loadMenu(@Context HttpServletRequest request){
        return menuService.loadMenu(getSessionUser(request).getRoleId());
    }


    /**
     * 功能描述: <br> 获取角色菜单
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/20 10:37
     */
    @GET
    @Path("roleMenu")
    @Produces(MediaType.APPLICATION_JSON)
    public List roleMenu(@QueryParam("roleId") String roleId)
    {
        return menuService.loadRoleMenu(roleId);
    }

   /**
    * 功能描述: <br> 修改菜单
    * @version: 1.0.0
    * @Author: jcj
    * @Date: 2018/9/18 17:31
    */
    @PUT
    @Path("menu/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(MenuVO menu, @PathParam("id")String id, @Context HttpServletRequest request){
        menu.setId(id);
        menuService.edit(menu);
        return ResponseUtil.success("修改成功");
    }

   /**
    * 功能描述: <br> 添加菜单
    * @version: 1.0.0
    * @Author: jcj
    * @Date: 2018/9/18 17:31
    */
    @POST
    @Path("menu")
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(MenuVO menu){
        menu.setId(IDMaker.get().nextId());
        menuService.add(menu);
        return ResponseUtil.success("添加成功");
    }

   /**
    * 功能描述: <br> 删除菜单
    * @version: 1.0.0
    * @Author: jcj
    * @Date: 2018/9/18 17:32
    */
    @DELETE
    @Path("menu/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modify(@PathParam("id")String id){
        menuService.remove(id);
        return ResponseUtil.success("删除成功");
    }
}
