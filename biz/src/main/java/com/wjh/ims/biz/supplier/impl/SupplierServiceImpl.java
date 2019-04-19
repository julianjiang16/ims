package com.wjh.ims.biz.supplier.impl;

import com.wjh.ims.biz.BaseServiceImpl;
import com.wjh.ims.biz.supplier.SupplierService;
import com.wjh.ims.common.constant.ImsConstant;
import com.wjh.ims.common.enums.NoTypeEnum;
import com.wjh.ims.common.utils.IDMaker;
import com.wjh.ims.common.utils.NumberUtils;
import com.wjh.ims.dal.mapper.supplier.SupplierMapper;
import com.wjh.ims.dal.mapper.supplier.ext.SupplierMapperExt;
import com.wjh.ims.dal.model.supplier.Supplier;
import com.wjh.ims.dal.model.supplier.SupplierExample;
import com.wjh.ims.dto.vo.supplier.SupplierVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SupplierServiceImpl extends BaseServiceImpl implements SupplierService {

    @Autowired
    SupplierMapper supplierMapper;

    @Autowired
    SupplierMapperExt supplierMapperExt;

    @Override
    public List<SupplierVO> getByCondition(String name, String no, int start, int rows) {
        SupplierExample example=new SupplierExample();
        example.setOrderByClause("gmt_create desc");
        example.setStart(start); example.setRows(rows);

        example=commonCondition(example,name,no);
        return mf.mapAsList(supplierMapper.selectByExample(example), SupplierVO.class);
    }

    @Override
    public int countByCondition(String name, String no) {
        SupplierExample example=new SupplierExample();

        example=commonCondition(example,name,no);
        return supplierMapper.countByExample(example);
    }

    @Override
    @Transactional
    public void addSupplier(SupplierVO supplierVO) throws Exception {
        supplierVO.setId(IDMaker.get().nextId());

        supplierVO.setGmtCreate(new Date());
        supplierVO.setGmtModified(new Date());
        supplierVO.setNo(getSupplierNo());
        supplierMapper.insertSelective(mf.map(supplierVO, Supplier.class));
    }

    @Override
    @Transactional
    public void editSupplier(SupplierVO supplierVO) {
        supplierVO.setGmtModified(new Date());
        // todo jcj 修改no检测
        supplierMapper.updateByPrimaryKeySelective(mf.map(supplierVO, Supplier.class));
    }

    private SupplierExample commonCondition(SupplierExample example, String name, String no){
        SupplierExample.Criteria c=example.createCriteria();
        if (!StringUtils.isEmpty(name)){
            c.andNameLike("%"+name+"%");
        }
        if (!StringUtils.isEmpty(no)){
            c.andNoEqualTo(no);
        }
        return example;
    }

    private String getSupplierNo() throws Exception {
        String supplierNo = supplierMapperExt.selectMaxSupplierNo();
        String no = NumberUtils.getNumStr(supplierNo, ImsConstant.SUPPLIER_NO_LENGTH, NoTypeEnum.SUPPLIER_NO);
        return no;
    }
}
