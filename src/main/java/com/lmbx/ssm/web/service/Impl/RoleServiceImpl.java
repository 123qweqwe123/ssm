package com.lmbx.ssm.web.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmbx.ssm.web.daomain.Role;
import com.lmbx.ssm.web.daomain.RolePermissionDTO;
import com.lmbx.ssm.web.mapper.RoleDao;
import com.lmbx.ssm.web.service.RoleService;


/**
 * 
 * 
 * <pre>
 * 功能说明：角色Service实现
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	//@Cacheable(value = "menuCache",key="#userId+'getRolesByUserId'")  
	public List<Role> getRolesByUserId(String userId) {
			return roleDao.queryByUserId(userId);
	}

	@Override
	public List<Role> getRolesByUserIdAndOrganId(String userId, String organId) {
			return roleDao.queryByUserIdAndOrganId(userId, organId);
	} 

	@Override
	@CacheEvict(value = "menuCache",allEntries = true)
	public String save(Role role) {
		return null;

		/*//String roleId = Identities.uuid();
		String roleId = GenerateKey.getKey(GenerateKey.PREFIX_ROLE);
		role.setId(roleId);
		role.setCreateUser(Securitys.getUserId());
		role.setOrganId(Securitys.getOrganId()); 
		role.setCreateDatetime(new Date());
		role.setIsDelete(0);
		if (checkExists(role.getName(), null)) {
			throw new ServiceException("角色名称已存在");
		}
		try {
			Integer indexNo = roleDao.queryMaxIndexNo(Securitys.getOrganId ());
			role.setIndexNo(indexNo);
			roleDao.save(role);
			//logService.info(Consts.SystemModel.SYS, "添加角色" + role.getName());
			return roleId;
		} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "添加角色失败");
			throw new ServiceException("添加角色失败", e);
		}*/

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.RoleService#getById(java.lang.
	 * String)
	 */
	@Override
	public Role getById(String roleId) {
		return null;
			//return roleDao.queryById(roleId, Securitys.getOrganId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.RoleService#getAll(java.lang.String
	 * )
	 */
	@Override
	//@Cacheable(value = "menuCache",key="'getAllRole'")  
	public List<Role> getAll() {
			return roleDao.queryAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.RoleService#getAll(java.lang.String
	 * )
	 */
	@Override
	//@Cacheable(value = "remindCache",key="#organId+'getAllByOrganId'")  
	public List<Role> getAllByOrganId(String organId) {
		/*try {
			Integer deptLevel = null;
			if(!"99".equals(Securitys.getUser().getLccCode())){
				deptLevel=1;
	    	}*/
			return roleDao.queryAllByOrganId(organId,1);
		/*} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "查询所有用户失败");
			throw new ServiceException("查询所有用户失败", e);
		}*/
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.RoleService#getRolesByFilter(com
	 * .genertech.adp.web.sys.rbac.filter.RoleFilter)
	 */
	/*@Override
	public List<Role> getRolesByFilter(RoleFilter filter) {
		filter.setOrganId (Securitys.getOrganId() );
		try {
			return roleDao.queryByFilter(filter);
		} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "按条件查询角色失败");
			throw new ServiceException("按条件查询角色失败", e);
		}
	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.RoleService#update(com.genertech
	 * .adp.web.sys.rbac.domain.Role)
	 */
	@Override
	@CacheEvict(value = "menuCache",allEntries = true)
	public String update(Role role) {

		role.setUpdateDatetime(new Date());
		return null;

		/*role.setUpdateUser(Securitys.getUserId());
		if (checkExists(role.getName(), role.getId())) {
			throw new ServiceException("角色名称已存在");
		}
		try {
			roleDao.update(role);

			//logService.info(Consts.SystemModel.SYS, "角色更新");

			return role.getId();

		} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "更新角色失败");
			throw new ServiceException("更新角色失败", e);
		}*/
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.RoleService#delete(java.lang.String
	 * )
	 */
	@Override
	@CacheEvict(value = "menuCache",allEntries = true)
	public void delete(String id) {

		/*try {
			String[] ids = { id };
			if (roleDao.queryUserRoleCount(ids) > 0) {
				throw new ServiceException("角色下存在用户关联不能删除");
			}*/
			roleDao.delete(id);
			roleDao.deleteRolePermission(id);
			//logService.warning(Consts.SystemModel.SYS, "删除角色" + id);
		/*} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "删除角色失败");
			throw new ServiceException("删除角色失败", e);
		}*/

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.RoleService#deleteByIds(java.util
	 * .List)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.RoleService#deleteByIds(java.util
	 * .List)
	 */
	@Override
	@CacheEvict(value = "menuCache",allEntries = true)
	public void deleteByIds(String[] ids) {
		/*try {
			if (roleDao.queryUserRoleCount(ids) > 0) {
				throw new ServiceException("角色下存在用户关联不能删除");
			}*/
			roleDao.deleteByIds(ids);
			roleDao.deleteRolePermissions(ids);
			//logService.warning(Consts.SystemModel.SYS, "批量删除角色");
		/*} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "批量删除角色失败");
			throw new ServiceException("批量删除角色失败", e);
		}*/

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.RoleService#save(com.genertech
	 * .adp.web.sys.rbac.domain.Role, java.lang.String[])
	 */
	@Override
	@CacheEvict(value = "menuCache",allEntries = true)
	public String save(Role role, String[] permissions) {

		return saveOrUpdateOrganModule(role, permissions, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.RoleService#update(com.genertech
	 * .adp.web.sys.rbac.domain.Role, java.lang.String[])
	 */
	@Override
	@CacheEvict(value = "menuCache",allEntries = true)
	public String update(Role role, String[] permissions) {

		return saveOrUpdateOrganModule(role, permissions, true);
	}

	/**
	 * 保存或更新角色权限
	 * 
	 * @param role
	 * @param permissions
	 * @param isUpdate
	 * @return
	 */
	@CacheEvict(value = "menuCache",allEntries = true)
	private String saveOrUpdateOrganModule(Role role, String[] permissions,
			Boolean isUpdate) {

		String roleId = null;

		// 如果更新，删除该角色之前的权限
		if (null != isUpdate && isUpdate) {
			// 更新角色
			roleId = update(role);

				// 删除该角色之前的权限
				roleDao.deleteRolePermission(roleId);
				// logService.warning(Consts.SystemModel.SYS, "删除角色关联的权限");

		} else {

			// 保存角色
			roleId = save(role);
		}

		if (null == permissions || permissions.length == 0) {
			return roleId;
		}

		List<RolePermissionDTO> rpList = new ArrayList<RolePermissionDTO>();

		RolePermissionDTO rp = null;

		// 构造角色权限集合
		for (String permissionId : permissions) {

			rp = new RolePermissionDTO(roleId, permissionId);

			rpList.add(rp);
		}

		// 更新角色权限
		if (!rpList.isEmpty()) {

				roleDao.saveRolePermission(rpList);
				//logService.info(Consts.SystemModel.SYS, "保存角色权限");
		}
		return roleId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.RoleService#getUserRoleCount(java
	 * .lang.String[])
	 */
	@Override
	public Long getUserRoleCount(String[] ids) {
			return roleDao.queryUserRoleCount(ids);
	}

	@Override
	public boolean checkExists(String name, String id) {
		return false;
		/*long count = roleDao.checkExists(name.trim(), id,
				Securitys.getOrganId() );
		return count > 0;*/
	}

	@Override
	@CacheEvict(value = "menuCache",allEntries = true)
	public void deleteByRoleId(String id) {
			roleDao.deleteByRoleId(id);

	}

	@Override
	public void addTailRole(String[] ids, String roleId) {
		StringBuffer sb = new StringBuffer();
		if (ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				sb.append("select  ");
				sb.append(" '");
				sb.append(roleId);
				sb.append("','");
				sb.append(ids[i]);
				sb.append("' ");
				sb.append(" from dual");
				if (i < ids.length - 1) {
					sb.append(" union ");
				}
			}
		}
			roleDao.addRoleTail(sb.toString());

	}

}
