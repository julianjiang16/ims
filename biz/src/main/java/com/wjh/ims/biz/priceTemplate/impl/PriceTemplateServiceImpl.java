package com.wjh.ims.biz.priceTemplate.impl;

import com.wjh.ims.biz.BaseServiceImpl;
import com.wjh.ims.biz.priceTemplate.PriceTemplateService;
import com.wjh.ims.common.constant.ImsConstant;
import com.wjh.ims.common.enums.NoTypeEnum;
import com.wjh.ims.common.utils.IDMaker;
import com.wjh.ims.common.utils.NumberUtils;
import com.wjh.ims.dal.mapper.priceTemplate.PriceTemplateMapper;
import com.wjh.ims.dal.mapper.priceTemplate.ext.PriceTemplateMapperExt;
import com.wjh.ims.dal.model.priceTemplate.PriceTemplate;
import com.wjh.ims.dal.model.priceTemplate.PriceTemplateExample;
import com.wjh.ims.dto.vo.priceTemplate.PriceTemplateVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PriceTemplateServiceImpl extends BaseServiceImpl implements PriceTemplateService {

    @Autowired
    PriceTemplateMapper priceTemplateMapper;

    @Autowired
    PriceTemplateMapperExt priceTemplateMapperExt;

    @Override
    public List<PriceTemplateVO> getByCondition(String name, String no,String type, int start, int rows) {
        PriceTemplateExample example=new PriceTemplateExample();
        example.setOrderByClause("gmt_create desc");
        example.setStart(start); example.setRows(rows);

        example = commonCondition(example, name, no,type);
        return mf.mapAsList(priceTemplateMapper.selectByExample(example), PriceTemplateVO.class);
    }

    @Override
    public int countByCondition(String name, String no,String type) {
        PriceTemplateExample example=new PriceTemplateExample();

        example = commonCondition(example,name,no,type);
        return priceTemplateMapper.countByExample(example);
    }

    @Override
    @Transactional
    public void addPriceTemplate(PriceTemplateVO priceTemplateVO) throws Exception {
        priceTemplateVO.setId(IDMaker.get().nextId());

        priceTemplateVO.setGmtCreate(new Date());
        priceTemplateVO.setGmtModified(new Date());
        priceTemplateVO.setNo(getPriceTemplateNo());
        priceTemplateMapper.insertSelective(mf.map(priceTemplateVO, PriceTemplate.class));
    }

    @Override
    @Transactional
    public void editPriceTemplate(PriceTemplateVO priceTemplateVO) {

        priceTemplateVO.setGmtModified(new Date());
        // todo jcj 修改no检测
        priceTemplateMapper.updateByPrimaryKeySelective(mf.map(priceTemplateVO, PriceTemplate.class));
    }

    private PriceTemplateExample commonCondition(PriceTemplateExample example, String name, String no, String type){
        PriceTemplateExample.Criteria c=example.createCriteria();
        if (!StringUtils.isEmpty(name)){
            c.andNameLike("%"+name+"%");
        }
        if (!StringUtils.isEmpty(no)){
            c.andNoEqualTo(no);
        }
        if (!StringUtils.isEmpty(type) && !ImsConstant.DEFAULT_COMMON_STATUS_VAL.equals(type)){
            c.andTypeEqualTo(type);
        }
        return example;
    }

    private String getPriceTemplateNo() throws Exception {
        String priceTemplateNo = priceTemplateMapperExt.selectMaxPriceTemplateNo();
        String no = NumberUtils.getNumStr(priceTemplateNo, ImsConstant.DEFAULT_COMMON_NO_LENGTH, NoTypeEnum.DEFAULT_NO);
        return no;
    }
}
