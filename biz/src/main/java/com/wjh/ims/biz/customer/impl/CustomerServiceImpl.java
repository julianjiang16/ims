package com.wjh.ims.biz.customer.impl;

import com.wjh.ims.biz.BaseServiceImpl;
import com.wjh.ims.biz.customer.CustomerService;
import com.wjh.ims.common.constant.ImsConstant;
import com.wjh.ims.common.enums.NoTypeEnum;
import com.wjh.ims.common.utils.IDMaker;
import com.wjh.ims.common.utils.NumberUtils;
import com.wjh.ims.dal.mapper.customer.CustomerMapper;
import com.wjh.ims.dal.mapper.customer.ext.CustomerMapperExt;
import com.wjh.ims.dal.model.customer.Customer;
import com.wjh.ims.dal.model.customer.CustomerExample;
import com.wjh.ims.dto.vo.customer.CustomerVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class CustomerServiceImpl extends BaseServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    CustomerMapperExt customerMapperExt;

    @Override
    public List<CustomerVO> getByCondition(String name, String no, int start, int rows) {
        CustomerExample example=new CustomerExample();
        example.setOrderByClause("gmt_create desc");
        example.setStart(start); example.setRows(rows);

        example=commonCondition(example,name,no);
        return mf.mapAsList(customerMapper.selectByExample(example), CustomerVO.class);
    }

    @Override
    public int countByCondition(String name, String no) {
        CustomerExample example=new CustomerExample();

        example=commonCondition(example,name,no);
        return customerMapper.countByExample(example);
    }

    @Override
    @Transactional
    public void addCustomer(CustomerVO customerVO) throws Exception {
        customerVO.setId(IDMaker.get().nextId());

        customerVO.setGmtCreate(new Date());
        customerVO.setGmtModified(new Date());
        customerVO.setNo(getCustomerNo());
        customerMapper.insertSelective(mf.map(customerVO, Customer.class));
    }

    @Override
    @Transactional
    public void editCustomer(CustomerVO customerVO) {
        customerVO.setGmtModified(new Date());
        // todo jcj 修改no检测
        customerMapper.updateByPrimaryKeySelective(mf.map(customerVO, Customer.class));
    }

    private CustomerExample commonCondition(CustomerExample example, String name, String no){
        CustomerExample.Criteria c=example.createCriteria();
        if (!StringUtils.isEmpty(name)){
            c.andNameLike("%"+name+"%");
        }
        if (!StringUtils.isEmpty(no)){
            c.andNoEqualTo(no);
        }
        return example;
    }

    private String getCustomerNo() throws Exception {
        String customerNo = customerMapperExt.selectMaxCustomerNo();
        String no = NumberUtils.getNumStr(customerNo, ImsConstant.CUSTOMER_NO_LENGTH, NoTypeEnum.CUSTOMER_NO);
        return no;
    }
}
