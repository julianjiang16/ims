package com.wjh.ims.biz.customer;

import com.wjh.ims.dto.vo.customer.CustomerVO;

import java.util.List;

/**
 * 客户服务接口
 */
public interface CustomerService {

    List<CustomerVO> getByCondition(String name,String no,int start,int rows);

    int countByCondition(String name,String no);

    void addCustomer(CustomerVO customerVO) throws Exception;

    void editCustomer(CustomerVO customerVO);
}
