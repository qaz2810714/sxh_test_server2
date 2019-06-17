package com.yongqiang.wms.model.user;

import com.yongqiang.wms.model.base.AbstractBaseModel;

import java.util.List;

/**
 * 菜单与权限点
 * @author kai.chen
 */
public class Permission extends AbstractBaseModel {
	/**
     * 账套ID
     */
    private Integer accountId;
	/**
     * 权限点ID
     */
    private Integer id;
    
    /**
     * 父权限点ID
     */
    private Integer parent;
    
    /**
     * 父节点路径
     */
    private String parentPath;
    
    /**
     * 权限点类型 三种   1 菜单  2 按钮   3目录
     */
    private Integer type;
    
    /**
     * VUE页面组件名称（动态指定组件）
     */
    private String componetName;
    
    /**
     * 图标样式
     */
    private String iconCls;
    
    /**
     * 权限点对应页面URL
     */
    private String url;
    
    /**
     * 权限点编码
     */
    private String code;
    
    /**
     * 权限点显示名称
     */
    private String name;
    
    /**
     * 权限点排序
     */
    private Integer seq;
    
    /**
     * 权限点备注
     */
    private String remark;
    
    /**
     * 子菜单权限
     */
    private List<Permission> child;
    
    

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Permission> getChild() {
		return child;
	}

	public void setChild(List<Permission> child) {
		this.child = child;
	}

    /**
     * @return the componetName
     */
    public String getComponetName() {
        return componetName;
    }

    /**
     * @param componetName the componetName to set
     */
    public void setComponetName(String componetName) {
        this.componetName = componetName;
    }

    /**
     * @return the iconCls
     */
    public String getIconCls() {
        return iconCls;
    }

    /**
     * @param iconCls the iconCls to set
     */
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }
	
}
