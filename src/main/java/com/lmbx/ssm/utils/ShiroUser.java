package com.lmbx.ssm.utils;


import java.io.Serializable;
import java.util.List;

/**
 * 
 * <pre>
 * 功能说明：自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * </pre>
 * 
 * @author <a href="mailto:liu.w@gener-tech.com">liuwei</a>
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ShiroUser implements Serializable {

	private String id;// UserId
	private String loginName;// loginName
	private String name;// name
	private boolean isAdmin;// 是否系统级管理员
	private String organId;           //人员对应的组织ID
	private String departmentId;      //所属部门ID
	private String companyId;         //所属公司ID
	private String current_projectId; //当前项目ID
	private String current_projectName; //当前项目ID
	private String lccCode; //单位ID
	private String lccName; //单位ID
	private String IP;
	private boolean isOrganAdmin;       
	private List<String> roleIdList;
	private String userCode;

	public ShiroUser(String id, String loginName, String name, Boolean isAdmin,
			String organId, String clientIp, Boolean isOrganAdmin,
			List<String> roleIdList, String projectId, String lccCode,String projectName,String lccName,String userCode) {
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.organId = organId;
		//this.tenantName = tenantName;
		this.IP = clientIp;
		this.isAdmin = isAdmin;
		this.isOrganAdmin = isOrganAdmin;
		this.roleIdList = roleIdList;
		this.current_projectId = projectId;
		this.lccCode = lccCode;
		this.current_projectName = projectName;
		this.lccName=lccName;
		this.userCode=userCode;
	}
	
	public ShiroUser(String id,String loginName,String name,Boolean isAdmin,String clientIp){
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.IP = clientIp;
		this.isAdmin = isAdmin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	} 
     
	public String getCurrent_projectName() {
		return current_projectName;
	}

	public void setCurrent_projectName(String current_projectName) {
		this.current_projectName = current_projectName;
	}

	public String getLccName() {
		return lccName;
	}

	public void setLccName(String lccName) {
		this.lccName = lccName;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	private void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getCompanyId() {
		return companyId;
	}

	private void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCurrent_projectId() {
		return current_projectId;
	}

	public void setCurrent_projectId(String current_projectId) {
		this.current_projectId = current_projectId;
	} 

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((loginName == null) ? 0 : loginName.hashCode());
		result = prime * result
				+ ((organId == null) ? 0 : organId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShiroUser other = (ShiroUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		if (organId == null) {
			if (other.organId != null)
				return false;
		} else if (!organId.equals(other.organId))
			return false;
		return true;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	/*public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}*/

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public boolean isOrganAdmin() {
		return isOrganAdmin;
	}

	public void setOrganAdmin(boolean isOrganAdmin) {
		this.isOrganAdmin = isOrganAdmin;
	}

	@Override
	public String toString() {
		return "ShiroUser [id=" + id + ", loginName=" + loginName + ", name="
				+ name + ", isAdmin=" + isAdmin + ", tenantId=" + organId
				+ ", IP=" + IP + ", isOrganAdmin=" + isOrganAdmin + "]";
	}

	public String getLccCode() {
		return lccCode;
	}

	public void setLccCode(String lccCode) {
		this.lccCode = lccCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
