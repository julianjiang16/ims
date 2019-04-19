package com.wjh.ims.biz.user;


import com.wjh.ims.dto.vo.user.UserVO;

import java.util.List;

/**
 * Copyright (C), 2015-2018
 * FileName: UserServiceImpl
 * Author:   jcj
 * Date:     2018/9/18 17:37
 * Description: 用户服务
 */
public interface UserService {
   /**
    * 功能描述: <br> 登录
    * @version: 1.0.0
    * @Author: jcj
    * @Date: 2018/9/18 17:39
    */
    UserVO login(String username, String password);
    /**
     * 功能描述: <br> 用户信息
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/18 17:39
     */
    List<UserVO> getUser(int start, int rows);
    /**
     * 功能描述: <br> 用户记录数
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/18 17:39
     */
    int countUser();

    /**
     * 功能描述: <br> 编辑、添加用户
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/21 14:41
     */
    void dealUser(UserVO userVO);

    void editPassword(String id, String password);
}
