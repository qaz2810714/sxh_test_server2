package com.yongqiang.wms.model.user;

/**
 * 功能权限
 */
public class ProxyPermission {
    /**
     * 菜单项主键
     */
    private Integer permissionId;
    //权限类型（1菜单，2功能点）
    private Integer type;
    //权限编码
    private String code;
    //菜单路径（类型为功能点则空白）
    public String url;
    //权限名称
    private String name;
    //父层节点
    private Integer parent;
    //父级节点路径
    private String parentPath;
    //排序
    private String seq;
    //系统ID
    private Integer systemId;

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
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

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer id) {
        this.permissionId = id;
    }

    public Integer getParent() {
        return parent;
    }
    public void setParent(Integer parent) {
        this.parent = parent;
    }
}
