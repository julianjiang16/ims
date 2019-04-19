package com.wjh.ims.dto.vo.customer;

import java.util.Date;

public class CustomerVO {
    
    private String id;

    
    private String name;

    
    private String no;

    
    private String abbr;

    
    private String address;

    
    private String contacts;

    
    private String contactsPhone;

    
    private String type;

    
    private String taxNum;

    
    private String remarks;

    
    private String bank;

    
    private String bankNo;

    
    private Date gmtCreate;

    
    private Date gmtModified;

    
    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    
    public String getNo() {
        return no;
    }

    
    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    
    public String getAbbr() {
        return abbr;
    }

    
    public void setAbbr(String abbr) {
        this.abbr = abbr == null ? null : abbr.trim();
    }

    
    public String getAddress() {
        return address;
    }

    
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    
    public String getContacts() {
        return contacts;
    }

    
    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    
    public String getContactsPhone() {
        return contactsPhone;
    }

    
    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone == null ? null : contactsPhone.trim();
    }

    
    public String getType() {
        return type;
    }

    
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    
    public String getTaxNum() {
        return taxNum;
    }

    
    public void setTaxNum(String taxNum) {
        this.taxNum = taxNum == null ? null : taxNum.trim();
    }

    
    public String getRemarks() {
        return remarks;
    }

    
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    
    public String getBank() {
        return bank;
    }

    
    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    
    public String getBankNo() {
        return bankNo;
    }

    
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo == null ? null : bankNo.trim();
    }

    
    public Date getGmtCreate() {
        return gmtCreate;
    }

    
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    
    public Date getGmtModified() {
        return gmtModified;
    }

    
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}