package com.yongqiang.wms.model.user;

import com.yongqiang.wms.model.base.AbstractBaseModel;

import java.util.List;

/**
 * 用户管理
 * @author kai.chen
 */
public class User extends AbstractBaseModel {
	 /**
     * 账套ID
     */
    private Integer accountId;
	/**
     * 员工ID
     */
    private Long id;
    
    /**
     * 登录名
     * 唯一性校验（全局唯一，包含逻辑删除的账号）
     */
    private String loginName;
    
    /**
     * 登录密码
     */
    private String loginPassword;
    
    /**
     * 旧密码
     */
    private String oldPassword;
    
    /**
     * 显示名
     */
    private String name;
    
    /**
     * 员工工号
     */
    private String number;
    
    /**
     * 部门Id
     */
    private Long departmentId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否内置用户
     */
    private Boolean fixedUser;
    
    
    private List<Long> roleIds;
    
    private List<Long> deptIds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getFixedUser() {
		return fixedUser;
	}

	public void setFixedUser(Boolean fixedUser) {
		this.fixedUser = fixedUser;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public List<Long> getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(List<Long> deptIds) {
		this.deptIds = deptIds;
	}
    
}
