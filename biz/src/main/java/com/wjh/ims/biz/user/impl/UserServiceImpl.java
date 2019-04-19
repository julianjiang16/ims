package com.wjh.ims.biz.user.impl;

import com.wjh.ims.biz.BaseServiceImpl;
import com.wjh.ims.biz.user.UserService;
import com.wjh.ims.biz.user.converter.UserConverter;
import com.wjh.ims.common.utils.IDMaker;
import com.wjh.ims.common.utils.MD5Util;
import com.wjh.ims.dal.mapper.user.UserMapper;
import com.wjh.ims.dal.mapper.user.ext.UserMapperExt;
import com.wjh.ims.dal.model.user.User;
import com.wjh.ims.dal.model.user.UserExample;
import com.wjh.ims.dto.vo.user.UserVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2015-2018
 * FileName: UserServiceImpl
 * Author:   jcj
 * Date:     2018/9/18 17:37
 * Description: 用户服务实现类
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserMapperExt userMapperExt;
    public UserVO login(String username, String password) {
        UserExample example=new UserExample();
        UserExample.Criteria c=example.createCriteria();
        if (username!=null)
            c.andUserNameEqualTo(username);
        if (password!=null)
            c.andPasswordEqualTo(password);
        List<UserVO> list=mf.mapAsList(userMapper.selectByExample(example),UserVO.class);
        if (list.size()>0)
            return list.get(0);
        return null;
    }

    public List<UserVO> getUser(int start, int rows) {
        return mf.mapAsList(userMapperExt.selectAllUser(), UserVO.class);
    }

    public int countUser() {
        return userMapper.countByExample(new UserExample());
    }

    public void dealUser(UserVO userVO) {
        // add
        if (StringUtils.isEmpty(userVO.getId()))
        {
            userVO.setId(IDMaker.get().nextId());
            userMapper.insertSelective(UserConverter.convertVOToDO(userVO));
            return;
        }
        // edit
        userMapper.updateByPrimaryKeySelective(UserConverter.convertVOToDO(userVO));
    }

    @Override
    public void editPassword(String id, String password) {
        User user=new User();
        user.setId(id);
        user.setPassword(MD5Util.MD5(password));
        userMapper.updateByPrimaryKeySelective(user);
    }
}
