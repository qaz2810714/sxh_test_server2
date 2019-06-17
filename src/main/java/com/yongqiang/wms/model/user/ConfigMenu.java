package com.yongqiang.wms.model.user;

import java.util.Date;

public class ConfigMenu {
    private Integer menuId;

    private String label;

    private Integer parentId;

    private Boolean isTailMenu;

    private Integer order;

    private String link;

    private String icon;

    private String description;

    private Integer creator;

    private Date createTime;

    private Integer modifier;

    private Date modifyTime;

    private Integer valid;

    private Integer subSystem;

    public Integer getSubSystem() {
        return subSystem;
    }

    public void setSubSystem(Integer subSystem) {
        this.subSystem = subSystem;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Boolean getIsTailMenu() {
        return isTailMenu;
    }

    public void setIsTailMenu(Boolean isTailMenu) {
        this.isTailMenu = isTailMenu;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}