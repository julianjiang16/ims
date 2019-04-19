package com.wjh.ims.dto.vo.priceTemplate;

public class GoodsPriceTemplateVO {
    
    private String id;

    
    private String goodsId;

    
    private String templateId;

    
    private Integer price;

    
    private Integer discount;

    
    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    
    public String getGoodsId() {
        return goodsId;
    }

    
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    
    public String getTemplateId() {
        return templateId;
    }

    
    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? null : templateId.trim();
    }

    
    public Integer getPrice() {
        return price;
    }

    
    public void setPrice(Integer price) {
        this.price = price;
    }

    
    public Integer getDiscount() {
        return discount;
    }

    
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}