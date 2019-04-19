package com.wjh.ims.biz.user.converter;

import com.google.common.collect.Lists;
import com.wjh.ims.common.utils.MD5Util;
import com.wjh.ims.dal.model.user.User;
import com.wjh.ims.dto.vo.user.UserVO;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Copyright (C), 2015-2018
 * FileName: UserConverter
 * Author:   jcj
 * Date:     2018/9/20 9:51
 * Description: 用户转换类
 */
public class UserConverter {

    private static final String PWD="password";

    /**
     * 功能描述: <br> vo to do
     * @since: 1.0.0
     * @Author:jcj
     * @Date: 2018/9/16 13:22
     */
    public static User convertVOToDO(UserVO userVO)
    {
        User user = new User();
        if (!StringUtils.isEmpty(userVO.getId()))
        {
            user.setId(userVO.getId());
            user.setPassword(MD5Util.MD5(PWD));
        }else {
            user.setPassword(userVO.getPassword());
        }
        user.setUserName(userVO.getUserName());
        user.setRoleId(userVO.getRoleId());
        user.setGmtCreate(new Date());
        return user;
    }

    /**
     * 功能描述: <br> vos to dos
     * @since: 1.0.0
     * @Author:jcj
     * @Date: 2018/9/16 13:22
     */
    public static List<User> convertListVOToListDO(List<UserVO> userVOS)
    {
        List<User> users = Lists.newArrayList();
        for (UserVO userVO : userVOS)
        {
            users.add(convertVOToDO(userVO));
        }
        return users;
    }

    /**
     * 功能描述: <br> vo to do
     * @since: 1.0.0
     * @Author:jcj
     * @Date: 2018/9/16 13:22
     */
    public static UserVO convertDOToVO(User user)
    {
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUserName(user.getUserName());
        userVO.setRoleId(user.getRoleId());
        userVO.setPassword(user.getPassword());
        userVO.setGmtCreate(new Date());
        return userVO;
    }

    /**
     * 功能描述: <br> vos to dos
     * @since: 1.0.0
     * @Author:jcj
     * @Date: 2018/9/16 13:22
     */
    public static List<UserVO> convertListDOToListVO(List<User> users)
    {
        List<UserVO> userVOS = Lists.newArrayList();
        for (User role : users)
        {
            userVOS.add(convertDOToVO(role));
        }
        return userVOS;
    }
}
