package com.yongqiang.wms.model.base;

import java.util.List;

/**
 * 业务对象树结构封装
 * 
 * @author LDR
 * @param <T>
 */
public class TreeNode<T extends AbstractBaseModel> {
	
	private Integer id; 
	
	private String name;
	
	private Integer type;
	
	private Integer parentId;
	
    /**
     * 当前节点
     */
    private T node;
    
    /**
     * 子节点
     */
    private List<TreeNode<T>> subNodes;

    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
     * @return the node
     */
    public T getNode() {
        return node;
    }

    /**
     * @param node the node to set
     */
    public void setNode(T node) {
        this.node = node;
    }

    /**
     * @return the subNodes
     */
    public List<TreeNode<T>> getSubNodes() {
        return subNodes;
    }

    /**
     * @param subNodes the subNodes to set
     */
    public void setSubNodes(List<TreeNode<T>> subNodes) {
        this.subNodes = subNodes;
    }
    
}
