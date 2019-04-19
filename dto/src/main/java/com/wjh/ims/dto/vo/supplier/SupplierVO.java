package com.wjh.ims.dto.vo.supplier;

import java.util.Date;

public class SupplierVO {
    
    private String id;

    
    private String name;

    
    private String no;

    
    private String abbr;

    
    private String address;

    
    private String contacts;

    
    private String contactsPhone;

    
    private String bank;

    
    private String bankNo;

    
    private String remarks;

    
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

    
    public String getRemarks() {
        return remarks;
    }

    
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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