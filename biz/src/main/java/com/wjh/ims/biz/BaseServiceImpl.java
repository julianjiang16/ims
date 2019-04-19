package com.wjh.ims.biz;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Copyright (C), 2015-2018
 * FileName: BaseServiceImpl
 * Author:   jcj
 * Date:     2018/9/18 17:37
 * Description: 基础服务实现类
 */
public class BaseServiceImpl implements BaseService {
    private static final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    public static final MapperFacade mf = mapperFactory.getMapperFacade();


}
