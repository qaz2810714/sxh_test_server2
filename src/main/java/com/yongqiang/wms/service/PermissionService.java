package com.yongqiang.wms.service;

import com.yongqiang.wms.mapper.PermissionMapper;
import com.yongqiang.wms.model.base.TreeNode;
import com.yongqiang.wms.model.user.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kai.chen
 *	权限菜单相关实现类
 */
@Service
public class PermissionService{

	@Autowired
	private PermissionMapper permissionMapper;
	
	public List<Permission> getAllPermission() {
		return permissionMapper.getAllPermission();
	}

//	public List<Permission> findPermissionsOfUser(Long userId) {
//		return permissionMapper.findPermissionsOfUser(userId);
//	}

//	@Override
//	public List<Permission> findPermissionsOfRole(Long roleId) {
//		return permissionMapper.findPermissionsOfRole(roleId);
//	}

	public List<TreeNode<Permission>> getPermissionTreeByList(List<Permission> permissionList) {
		Map<Integer, List<Permission>> map = new HashMap<Integer, List<Permission>>();
		// 所有权限按照parentID进行分组
		for (Permission p : permissionList) {
			Integer key = 0;
			if (p.getParent() != null) {
				key = p.getParent();
			}
			// Map不包含指定组时
			if (!map.containsKey(key)) {
				List<Permission> mapList = new ArrayList<Permission> ();
				map.put(key, mapList);
			}
			// 填充到Map
			map.get(key).add(p);
		}
		// 分组Map转为树结构
		List<TreeNode<Permission>> resultList = new ArrayList<TreeNode<Permission>> ();
		if (map.isEmpty()) {
			return resultList;
		}
		// 遍历顶层，然后每个root节点内部循环递归填充
		if (map.get(0) != null) {
			for (Permission rootP : map.get(0)) {
				TreeNode<Permission> node = new TreeNode<Permission>();
				node.setNode(rootP);
				loopFillNode(node, map);
				resultList.add(node);
			}
		}
		return resultList;
	}

	/**
	 * Tree结构数据递归填充
	 * 
	 * @param node
	 * @param map
	 */
	private void loopFillNode(TreeNode<Permission> node, Map<Integer, List<Permission>> map) {
		Integer parentID = node.getNode().getId();
		// 分组Map转为树结构
		List<TreeNode<Permission>> subNodes = new ArrayList<TreeNode<Permission>>();
		// 遍历顶层，然后每个root节点内部循环递归填充
		List<Permission> mapList = map.get(parentID);
		if (mapList == null) {
			return;
		}
		for (Permission subP : mapList) {
			TreeNode<Permission> subNode = new TreeNode<Permission>();
			subNode.setNode(subP);
			loopFillNode(subNode, map);
			subNodes.add(subNode);
		}
		// 填充所有子到node
		node.setSubNodes(subNodes);
	}

	
}
