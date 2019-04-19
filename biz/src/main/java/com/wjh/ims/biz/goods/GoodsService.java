package com.wjh.ims.biz.goods;

import com.wjh.ims.dto.vo.goods.GoodsTypeVO;
import com.wjh.ims.dto.vo.goods.GoodsVO;

import java.util.List;

/**
 * 商品服务接口
 */
public interface GoodsService {

    List<GoodsVO> getByCondition(String name,String no,String py,String type,int start,int rows);

    int countByCondition(String name,String no,String py,String type);

    void addGoods(GoodsVO goodsVO) throws Exception;

    void editGoods(GoodsVO goodsVO) throws Exception;

    List<GoodsTypeVO> getType();

    void addGoodsType(GoodsTypeVO goodsTypeVO) throws Exception;

    void editGoodsType(GoodsTypeVO goodsTypeVO);
}
