package com.wjh.ims.dto.vo.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2015-2018
 * FileName: MenuVO
 * Author:   jcj
 * Date:     2018/9/16 11:49
 * Description: 菜单vo
 */
public class MenuVO {

    /**  菜单名【extjs只认text属性作为菜单名】 */
    private String text;

    /** 所属子菜单 */
    private List<MenuVO> children = new ArrayList<MenuVO>();

    private String id;


    private String name;


    private String nodeId;


    private String qtitle;


    private Boolean leaf;


    private String systemId;


    private Byte level;


    private String icon;


    private String parentId;

    
    private Boolean checked;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

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


    public String getNodeId() {
        return nodeId;
    }


    public void setNodeId(String nodeId) {
        this.nodeId = nodeId == null ? null : nodeId.trim();
    }


    public String getQtitle() {
        return qtitle;
    }


    public void setQtitle(String qtitle) {
        this.qtitle = qtitle == null ? null : qtitle.trim();
    }


    public Boolean getLeaf() {
        return leaf;
    }


    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }


    public String getSystemId() {
        return systemId;
    }


    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }


    public Byte getLevel() {
        return level;
    }


    public void setLevel(Byte level) {
        this.level = level;
    }


    public String getIcon() {
        return icon;
    }


    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }


    public String getParentId() {
        return parentId;
    }


    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public void addChildren(MenuVO menuExt) {
        this.children.add(menuExt);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<MenuVO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVO> children) {
        this.children = children;
    }
}
