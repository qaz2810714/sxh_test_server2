package com.yongqiang.wms.controller;

import com.yongqiang.wms.model.base.ResponseJson;
import com.yongqiang.wms.model.base.TreeNode;
import com.yongqiang.wms.model.user.Permission;
import com.yongqiang.wms.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yantao.chen on 2019-05-21
 */
@RestController
@RequestMapping("api/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    /**
     * 获取权限tree
     * @return
     */
    @RequestMapping(value = "/getPermissionTree", method = {RequestMethod.POST})
    public ResponseJson getPermissionTree(  ) {
        //查询所有有效权限
        List<Permission> permissionList = permissionService.getAllPermission();
        //将所有有效权限转成权限树
        List<TreeNode<Permission>> permissionTrees = permissionService.getPermissionTreeByList(permissionList);

        return new ResponseJson(permissionTrees);
    }
}
