package com.yongqiang.wms.model.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限数据集合
 */
public class AuthData {
    public AuthData(){
        apis = new ArrayList<>();
        menus = new ArrayList<>();
        whsIds = new ArrayList<>();
        workShopIds = new ArrayList<>();
        departmentIds = new ArrayList<>();
        parentMenus = new HashSet<>();
    }
    //api权限
    private List<ConfigAPI> apis;
    //菜单权限
    private List<ConfigMenu> menus;
    //仓库权限
    private List<Integer> whsIds;
    //车间权限
    private List<Integer> workShopIds;
    //部门权限
    private List<Integer> departmentIds;
    //父级菜单
    private Set<Integer> parentMenus;

    public List<ConfigAPI> getApis() {
        return apis;
    }

    public void setApis(List<ConfigAPI> apis) {
        this.apis = apis;
    }

    public List<ConfigMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<ConfigMenu> menus) {
        this.menus = menus;
    }

    public List<Integer> getWhsIds() {
        return whsIds;
    }

    public void setWhsIds(List<Integer> whsIds) {
        this.whsIds = whsIds;
    }

    public List<Integer> getWorkShopIds() {
        return workShopIds;
    }
    public void setWorkShopIds(List<Integer> workShopIds) {
        this.workShopIds = workShopIds;
    }

    public List<Integer> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Integer> departmentIds) {
        this.departmentIds = departmentIds;
    }

    public Set<Integer> getParentMenus() {
        return parentMenus;
    }

    public void setParentMenus(Set<Integer> parentMenus) {
        this.parentMenus = parentMenus;
    }
}
