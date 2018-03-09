package com.lmbx.ssm.web.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmbx.ssm.web.daomain.Permission;
import com.lmbx.ssm.web.mapper.PermissionDao;
import com.lmbx.ssm.web.service.PermissionService;


@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#save(com.genertech
	 * .adp.web.sys.rbac.domain.Permission)
	 */
	@Override
	@CacheEvict(value = "menuCache", allEntries = true)
	public void save(Permission permission) {

		//permission.setId(Identities.uuid());
		Long indexNo = permissionDao.queryMaxIndexNoByParentId(permission
				.getParentId());
		if (null == indexNo) {
			Long level = permission.getGrade();
			indexNo = Math.round(Math.pow(10, level.doubleValue()));
		}
		permission.setIndexNo(indexNo);
		permission.setCreateDatetime(new Date());
		//permission.setCreateUser(Securitys.getUserId());
		permission.setIsDelete(0);
			permissionDao.save(permission);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bdcor.pip.web.sys.rbac.service.PermissionService#
	 * getPermissionsByRoleId(java.lang.String)
	 */
	@Override
	//@Cacheable(value = "menuCache",key="#roleId+'getPermissionsByRoleId'") 
	public List<Permission> getPermissionsByRoleId(String roleId) {
			return permissionDao.queryByRoleId(roleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#getAllPermissions
	 * ()
	 */
	@Override
	//@Cacheable(value = "menuCache",key="'getAllPermissions'") 
	public List<Permission> getAllPermissions() {
			return permissionDao.queryAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#update(com.genertech
	 * .adp.web.sys.rbac.domain.Permission)
	 */
	@Override
	@CacheEvict(value = "menuCache",allEntries = true)
	public void update(Permission permission) {
		//permission.setUpdateUser(Securitys.getUserId());
		permission.setUpdateDatetime(new Date());
			permissionDao.update(permission);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#rename(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public void rename(String id, String newName) {

		Permission permission = new Permission();

		permission.setId(id);

		permission.setName(newName);
			permissionDao.rename(permission);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#delete(java.
	 * lang.String)
	 */
	@Override
	@CacheEvict(value = "menuCache",allEntries = true)
	public void delete(String id) {
			permissionDao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#getByUserId(
	 * java.lang.String)
	 */
	@Override
	//@Cacheable(value = "menuCache",key="#userId+'getPermissionByUserId'") 
	public List<Permission> getByUserId(String userId) {
			return permissionDao.queryByUserId(userId);
	}

	@Override
	public List<Permission> queryByUserIdCatHelp(String userId) {
			return permissionDao.queryByUserIdCatHelp(userId);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#getByTenantId
	 * (java.lang.String)
	 */
	@Override
	//@Cacheable(value = "menuCache",key="#organId+'getPermByOrganId'") 
	public List<Permission> getByOrganId(String organId) {
			return permissionDao.queryByOrganId(organId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#getByCode(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public Permission getByCode(String code, String userId) {
			return permissionDao.querByCodeAndUserId(code, userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#getByParentId
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public Permission getByParentId(String parentId, String userId) {
			return permissionDao.querByParentIdAndUserId(parentId, userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bdcor.pip.web.sys.rbac.service.PermissionService#
	 * getAllModulePermissions()
	 */
	@Override
	public List<Permission> getAllModulePermissions() {
			return permissionDao.queryAllModulePermissions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bdcor.pip.web.sys.rbac.service.PermissionService#
	 * getPermissionsByModuleId(java.lang.String)
	 */
	@Override
	public List<Permission> getPermissionsByModuleId(String id) {
			return permissionDao.queryPermissionsByModuleId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#getByCodeAndTenantId
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public Permission getByCodeAndOrganId(String code, String organId) {
			return permissionDao.queryByCodeAndOrganId(code, organId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bdcor.pip.web.sys.rbac.service.PermissionService#
	 * getByParentIdAndTenantId(java.lang.String, java.lang.String)
	 */
	@Override
	public Permission getByParentIdAndOrganId(String parentId, String organId) {
			Integer permissionType = 0;
			/*if (Securitys.isAdmin()) {
				permissionType = 1;
			}*/
			return permissionDao.querByParentIdAndOrganId(parentId, organId,
					permissionType);
	}

	@Override
	public List<Permission> getAllPermissionsForMenu() {
			return permissionDao.queryAllForMenu();
	}

	@Override
	public List<Permission> getPermissionByOrganId(String organId) {
			return permissionDao.getPermissionByOrganId(organId);
	}

	@Override
	public List<Permission> getAllPermissionsWithOutBase() {
			return permissionDao.queryAllWithOutBase();
	}

	@Override
	public Permission checkNameExists(Permission permission) {
		return permissionDao.checkNameExists(permission);
	}



}
