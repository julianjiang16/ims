package com.wjh.ims.back.component.user;

import com.wjh.ims.back.Constant;
import com.wjh.ims.back.ResponseUtil;
import com.wjh.ims.biz.user.UserService;
import com.wjh.ims.common.utils.MD5Util;
import com.wjh.ims.dto.vo.user.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
 * FileName: UserComponent
 * Author:   jcj
 * Date:     2018/9/18 17:22
 * Description: 用户控制器
 */
@Component
@Path("/user/")
public class UserComponent {

    private static final Logger logger = LoggerFactory.getLogger(UserComponent.class);
    
    @Autowired
    UserService userService;

   /**
    * 功能描述: <br> 登录
    * @version: 1.0.0
    * @Author: jcj
    * @Date: 2018/9/18 17:32
    */
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("loginName")String userName,@FormParam("password")String password,
                          @Context HttpServletRequest request){
        try {
            UserVO user=userService.login(userName, MD5Util.MD5(password));
            if(user!=null)
            {
                request.getSession().setAttribute(Constant.COOKIE_USER_KEY,user);
                return ResponseUtil.success("登录成功");
            }else {
                return ResponseUtil.failure("登录失败");
            }
        }catch (Exception e){
            logger.info("user login fail:"+e.getMessage());
            return ResponseUtil.failure(null);
        }
    }

   /**
    * 功能描述: <br> 退出登录
    * @version: 1.0.0
    * @Author: jcj
    * @Date: 2018/9/18 17:32
    */
    @POST
    @Path("loginout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginOut(@Context HttpServletRequest request , @Context HttpServletResponse response){
        request.getSession().removeAttribute(Constant.COOKIE_USER_KEY);
        return ResponseUtil.success("退出成功");
    }

   /**
    * 功能描述: <br> 用户信息
    * @version: 1.0.0
    * @Author: jcj
    * @Date: 2018/9/18 17:32
    */
    @GET
    @Path("user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response user(@QueryParam("start") int start,@QueryParam("rows") int rows){
        List<UserVO> list=userService.getUser(start,rows);
        int count=userService.countUser();
        return ResponseUtil.data(list,count);
    }

    /**
     * 功能描述: <br> 添加用户信息
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/21 14:37
     */
    @POST
    @Path("user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response user(UserVO userVO){
        try {
            userService.dealUser(userVO);
            return ResponseUtil.success("处理成功");
        }catch (Exception e){
            return ResponseUtil.failure("处理失败，错误日志："+e.getMessage());
        }
    }

    /**
     * 功能描述: <br> 修改
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/21 15:28
     */
    @PUT
    @Path("user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response user(UserVO userVO, @PathParam("id") String id){
        try {
            userVO.setId(id);
            userService.dealUser(userVO);
            return ResponseUtil.success("处理成功");
        }catch (Exception e){
            return ResponseUtil.failure("处理失败，错误日志："+e.getMessage());
        }
    }

    /**
     * 功能描述: <br> 修改密码
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/10/24 18:00
     */
    @PUT
    @Path("password")
    @Produces(MediaType.APPLICATION_JSON)
    public Response user(@FormParam("password")String password,@Context HttpServletRequest request){
        try {
            UserVO userVO= (UserVO) request.getSession().getAttribute(Constant.COOKIE_USER_KEY);
            userService.editPassword(userVO.getId(), password);
            request.getSession().removeAttribute(Constant.COOKIE_USER_KEY);
            return ResponseUtil.success("处理成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.failure("处理失败，错误日志："+e.getMessage());
        }
    }
}
