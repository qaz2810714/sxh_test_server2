package com.yongqiang.wms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yongqiang.wms.common.constant.AuthConstant;
import com.yongqiang.wms.common.utils.BeanUtils;
import com.yongqiang.wms.common.utils.MD5Support;
import com.yongqiang.wms.model.base.RequestJson;
import com.yongqiang.wms.model.base.ReturnJson;
import com.yongqiang.wms.model.user.AuthData;
import com.yongqiang.wms.model.user.ConfigAPI;
import com.yongqiang.wms.model.user.ConfigMenu;
import com.yongqiang.wms.model.user.ConfigUser;
import com.yongqiang.wms.model.user.LoginForm;
import com.yongqiang.wms.model.user.LoginResultDto;
import com.yongqiang.wms.model.user.Permission;
import com.yongqiang.wms.model.user.ProxyPermission;
import com.yongqiang.wms.model.user.User;
import com.yongqiang.wms.service.PermissionService;
import com.yongqiang.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yantao.chen on 2018/10/24.
 * 用户登录权限类
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;
    /**
     * 用户登录
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ReturnJson login(@RequestBody RequestJson<LoginForm> model, HttpServletRequest request) {
        LoginForm data = model.getData();
//        ConfigUser configUser = userProxy.checkLogin(data.getUsername(),data.getPassword());
//        if(configUser == null) {
//            return new ReturnJson(1003, "无效的用户密码.");
//        }

        User user=new User();
//        user.setAccountId(data.getAccountId());
        user.setLoginName(data.getUsername());
        user.setLoginPassword(MD5Support.MD5(data.getPassword()));

//    	//检查账号是否被锁定
//        String redisKey=RedisKeyConstant.PREFIX_PASSWORD_LOCK+data.getAccountId()+"_"+data.getUsername();
//        String clientIp= getIpAddresses(request);
//    	userService.redisLockCheck(redisKey,clientIp);

        user=userService.selectUserByLogin(user);

		ConfigUser configUser = new ConfigUser();
		configUser.setUserId(user.getId());
		configUser.setAccount(user.getLoginName());
		configUser.setName(user.getName());
		configUser.setFixedUser(user.getFixedUser());
        LoginResultDto loginDto = JSONObject.toJavaObject((JSON) JSONObject.toJSON(configUser),LoginResultDto.class);

        if (loginDto == null){
            return new ReturnJson(1001, "公共请求参数为空.");
        }
        //暂时启用所有权限
//        AuthData authData = permissionService.findPermissionsOfUser(user.getId());
        AuthData authData = wrapPermission(permissionService.getAllPermission());
        List<ConfigMenu> menus = authData.getMenus();
        List<ConfigAPI> apis = authData.getApis();
        HashMap<String, List<String>> authorities = new HashMap<>();
        for (ConfigMenu menu : menus) {
            List<String> list = new ArrayList<>();
            for (ConfigAPI api : apis) {
                if (api.getMenuId() == (int) menu.getMenuId()) {
                    list.add(api.getApiCode());
                }
            }
            String link = menu.getLink();
            if (link == null) {
                continue;
            }
            authorities.put(link, list);
        }
        loginDto.setAuthorities(authorities);
        loginDto.setConfigUser(configUser);
        return new ReturnJson(loginDto);
    }

    /**
     * 功能权限数据解析
     */
    public AuthData wrapPermission(List<Permission> permissions){
        AuthData data = new AuthData();
        if(permissions == null) {
            return data;
        }
        for (Permission permission :permissions) {
            ProxyPermission pp = BeanUtils.copyProperties(permission, ProxyPermission.class);
            pp.setPermissionId(permission.getId());
            if(permission.getSeq()==null){
                permission.setSeq(0);
            }
            pp.setSeq(permission.getSeq().toString());
            //组装菜单
            wrapMenu(data,pp);
            //组装按钮
            wrapApi(data,pp);
        }
        //分析子菜单
        setTail(data);
        return data;
    }

    /**
     * 组装API
     * @param data
     * @param obj
     */
    private void wrapApi(AuthData data,ProxyPermission obj){
        if(!obj.getType().equals(AuthConstant.FUNC_API)) return;
        ConfigAPI api = new ConfigAPI();
        api.setApiName(obj.getName());
        //url存放编码
        api.setApiCode(obj.getUrl());
        //设置父级菜单
        api.setMenuId(obj.getParent());
        data.getApis().add(api);
    }

    /**
     * 组装菜单
     * @param data
     * @param obj
     */
    private void wrapMenu(AuthData data,ProxyPermission obj){
        if(!(obj.getType().equals(AuthConstant.FUNC_Folder)
                ||obj.getType().equals(AuthConstant.FUNC_Menu)))return;
        ConfigMenu menu = new ConfigMenu();
        menu.setMenuId(obj.getPermissionId());
        menu.setParentId(obj.getParent());
        menu.setLabel(obj.getName());
        menu.setLink(obj.getUrl());
        //用描述接收code字段，主要为排序用
        menu.setDescription(obj.getSeq());
        menu.setSubSystem(obj.getSystemId());
        try{
            menu.setOrder(Integer.parseInt(obj.getSeq()));
        }catch(Exception ex){
            menu.setOrder(null);
        }
        data.getMenus().add(menu);
        if(obj.getParent()!=null){
            data.getParentMenus().add(obj.getParent());
        }
    }

    /**
     * 设置菜单项的tail属性
     * @param data
     */
    private void setTail(AuthData data){
        for(ConfigMenu menu :data.getMenus()){
            menu.setIsTailMenu(false);
            for(Integer pId:data.getParentMenus()){
                if(menu.getMenuId().equals(pId)){
                    menu.setIsTailMenu(true);
                    data.getParentMenus().remove(pId);
                    break;
                }
            }
        }
    }
}
