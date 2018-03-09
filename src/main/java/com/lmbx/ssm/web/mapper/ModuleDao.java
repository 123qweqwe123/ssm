/**
 * ModuleDao.java Create on 2013-7-2
 * Copyright(c) Gener-Tech Inc.
 * ALL Rights Reserved.
 */
package com.lmbx.ssm.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lmbx.ssm.utils.MyBatisRepository;
import com.lmbx.ssm.web.daomain.Module;
import com.lmbx.ssm.web.daomain.ModulePermissionDTO;


/**
 * <pre>
 * 功能说明：
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
@MyBatisRepository
public interface ModuleDao {

	public void save(Module module);
	
	List<Module> queryAll();
	
	public void update(Module module);
	
	public void delete(String id);
	
	public Module queryById(String id);

	//public List<Module> queryByFilter(ModuleFilter filter);
	
	public Integer queryMaxIndex();
	
	public void deleteByIds(String[] ids);

    public List<Module> queryByOrganId(String OrganId);   

	public void deleteModulePermission(String moduleId);

	public void saveModulePermissions(List<ModulePermissionDTO> mpList);
	
	long checkExists(@Param("name")String name,@Param("id")String id);
}
