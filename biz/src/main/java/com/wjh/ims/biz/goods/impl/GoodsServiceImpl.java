package com.wjh.ims.biz.goods.impl;

import com.wjh.ims.biz.BaseServiceImpl;
import com.wjh.ims.biz.goods.GoodsService;
import com.wjh.ims.common.constant.ImsConstant;
import com.wjh.ims.common.enums.NoTypeEnum;
import com.wjh.ims.common.utils.IDMaker;
import com.wjh.ims.common.utils.NumberUtils;
import com.wjh.ims.common.utils.SpellUtils;
import com.wjh.ims.dal.mapper.goods.GoodsMapper;
import com.wjh.ims.dal.mapper.goods.GoodsTypeMapper;
import com.wjh.ims.dal.mapper.goods.ext.GoodsMapperExt;
import com.wjh.ims.dal.mapper.goods.ext.GoodsTypeMapperExt;
import com.wjh.ims.dal.model.goods.Goods;
import com.wjh.ims.dal.model.goods.GoodsExample;
import com.wjh.ims.dal.model.goods.GoodsType;
import com.wjh.ims.dal.model.goods.GoodsTypeExample;
import com.wjh.ims.dto.vo.goods.GoodsTypeVO;
import com.wjh.ims.dto.vo.goods.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl extends BaseServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GoodsTypeMapper goodsTypeMapper;

    @Autowired
    GoodsTypeMapperExt goodsTypeMapperExt;

    @Autowired
    GoodsMapperExt goodsMapperExt;

    @Override
    public List<GoodsVO> getByCondition(String name, String no, String py,String type, int start, int rows) {
        GoodsExample example=new GoodsExample();
        example.setOrderByClause("gmt_create desc");
        example.setStart(start);example.setRows(rows);
        example=commonCondition(example,name,no,py,type);
        return mf.mapAsList(goodsMapper.selectByExample(example),GoodsVO.class);
    }

    @Override
    public int countByCondition(String name, String no, String py,String type) {
        GoodsExample example=new GoodsExample();
        example=commonCondition(example,name,no,py,type);
        return goodsMapper.countByExample(example);
    }

    @Override
    @Transactional
    public void addGoods(GoodsVO goodsVO) throws Exception {
        goodsVO.setGmtCreate(new Date());
        goodsVO.setGmtModified(new Date());
        // 商品类型 + 商品编号
        goodsVO.setGoodsNo(getGoodsNo(goodsVO.getTypeNo()));

        goodsVO.setSpell(SpellUtils.getAlpha(goodsVO.getGoodsName()));
        goodsVO.setId(IDMaker.get().nextId());

        goodsMapper.insertSelective(mf.map(goodsVO, Goods.class));
    }

    @Override
    public void editGoods(GoodsVO goodsVO) throws Exception {
        goodsVO.setGmtModified(new Date());
        goodsVO.setGoodsNo(getGoodsNo(goodsVO.getTypeNo()));
        goodsMapper.updateByPrimaryKeySelective(mf.map(goodsVO, Goods.class));
    }

    @Override
    public List<GoodsTypeVO> getType() {
        return mf.mapAsList(goodsTypeMapper.selectByExample(new GoodsTypeExample()),GoodsTypeVO.class);
    }

    @Override
    @Transactional
    public void addGoodsType(GoodsTypeVO goodsTypeVO) throws Exception {
        String no = NumberUtils.getNumStr(goodsTypeMapperExt.selectMaxNo(), ImsConstant.TYPE_NO_LENGTH, NoTypeEnum.GOODS_TYPE_NO);
        goodsTypeVO.setNo(no);
        goodsTypeMapper.insertSelective(mf.map(goodsTypeVO, GoodsType.class));
    }

    @Override
    @Transactional
    public void editGoodsType(GoodsTypeVO goodsTypeVO) {
        goodsTypeMapper.updateByPrimaryKeySelective(mf.map(goodsTypeVO, GoodsType.class));
    }

    private GoodsExample commonCondition(GoodsExample example,String name,String no,String py,String type){
        GoodsExample.Criteria c=example.createCriteria();
        if (!StringUtils.isEmpty(name)){
            c.andGoodsNameLike("%"+name+"%");
        }
        if (!StringUtils.isEmpty(no)){
            c.andGoodsNoEqualTo(no);
        }
        if (!StringUtils.isEmpty(py)){
            c.andSpellEqualTo(py.toUpperCase());
        }
        if (!StringUtils.isEmpty(type)){
            c.andTypeNoEqualTo(type);
        }
        return example;
    }

    private String getGoodsNo(String typeNo) throws Exception {
        String goodsNo = goodsMapperExt.selectMaxGoodsNo(typeNo);
        goodsNo = StringUtils.isEmpty(goodsNo) ? null : goodsNo.substring(ImsConstant.TYPE_NO_LENGTH);
        String no = NumberUtils.getNumStr(goodsNo, ImsConstant.GOODS_NO_LENGTH, NoTypeEnum.GOODS_NO);
        return typeNo + no;
    }
}
