/**
 * RolePermissionDTO.java Create on 2013-7-9
 * Copyright(c) Gener-Tech Inc.
 * ALL Rights Reserved.
 */
package com.lmbx.ssm.web.daomain;

/**
 * <pre>
 * 功能说明：角色权限DTO
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
public class RolePermissionDTO {

    private String roleId;
    
    private String permissionId;

    public RolePermissionDTO() {}

    public RolePermissionDTO(String roleId, String permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
    
}
