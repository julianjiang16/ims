package com.wjh.ims.dal.mapper.goods.ext;

import org.apache.ibatis.annotations.Param;

public interface GoodsMapperExt {

    String selectMaxGoodsNo(@Param("type_no")String typeNo);
}