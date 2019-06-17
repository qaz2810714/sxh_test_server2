package com.yongqiang.wms.model.user;

/**
 * Created by yantao.chen on 2018/10/24.
 */
public class LoginForm {
	 /**
     * 账套ID
     */
    private Integer accountId;
    private String username;
    private String password;
    private Integer systemId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
}
