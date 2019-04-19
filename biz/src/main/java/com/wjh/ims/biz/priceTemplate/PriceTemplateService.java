package com.wjh.ims.biz.priceTemplate;

import com.wjh.ims.dto.vo.priceTemplate.PriceTemplateVO;

import java.util.List;

/**
 * 价格模板服务接口
 */
public interface PriceTemplateService {

    List<PriceTemplateVO> getByCondition(String name, String no,String type, int start, int rows);

    int countByCondition(String name,String no,String type);

    void addPriceTemplate(PriceTemplateVO priceTemplateVO) throws Exception;

    void editPriceTemplate(PriceTemplateVO priceTemplateVO);
}
