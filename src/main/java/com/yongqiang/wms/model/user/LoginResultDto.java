package com.yongqiang.wms.model.user;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yantao.chen on 2018/10/24.
 */
public class LoginResultDto {
	
    private String token;
    private int userId;
    private String session;
    private int institutionId;
    private int departmentId;
    private int employeeId;

    private ConfigUser configUser;

    private List<Integer> instIds;
    private List<Integer> whsIds;
    private List<Integer> workshopIds;
    //菜单权限
    private List<Permission> menus; 
    //按钮权限
    private List<String> btnPermissions; 
    

    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    private HashMap<String, List<String>> authorities;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public int getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(int institutionId) {
        this.institutionId = institutionId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public HashMap<String, List<String>> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(HashMap<String, List<String>> authorities) {
        this.authorities = authorities;
    }

    public List<Integer> getInstIds() {
        return instIds;
    }

    public void setInstIds(List<Integer> instIds) {
        this.instIds = instIds;
    }

    public ConfigUser getConfigUser() {
        return configUser;
    }

    public void setConfigUser(ConfigUser configUser) {
        this.configUser = configUser;
    }

    public List<Integer> getWhsIds() {
        return whsIds;
    }

    public void setWhsIds(List<Integer> whsIds) {
        this.whsIds = whsIds;
    }

    public List<Integer> getWorkshopIds() {
        return workshopIds;
    }

    public void setWorkshopIds(List<Integer> workshopIds) {
        this.workshopIds = workshopIds;
    }
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<Permission> getMenus() {
		return menus;
	}
	public void setMenus(List<Permission> menus) {
		this.menus = menus;
	}
	public List<String> getBtnPermissions() {
		return btnPermissions;
	}
	public void setBtnPermissions(List<String> btnPermissions) {
		this.btnPermissions = btnPermissions;
	}
}
