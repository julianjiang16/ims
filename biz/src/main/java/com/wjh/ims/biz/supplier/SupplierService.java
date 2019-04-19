package com.wjh.ims.biz.supplier;

import com.wjh.ims.dto.vo.supplier.SupplierVO;

import java.util.List;

/**
 * 供应商服务接口
 */
public interface SupplierService {

    List<SupplierVO> getByCondition(String name, String no, int start, int rows);

    int countByCondition(String name, String no);

    void addSupplier(SupplierVO supplierVO) throws Exception;

    void editSupplier(SupplierVO supplierVO);
}
