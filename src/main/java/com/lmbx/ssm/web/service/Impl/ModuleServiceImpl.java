/**
 * ModuleServiceImpl.java Create on 2013-7-2
 * Copyright(c) Gener-Tech Inc.
 * ALL Rights Reserved.
 */
package com.lmbx.ssm.web.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmbx.ssm.web.daomain.Module;
import com.lmbx.ssm.web.daomain.ModulePermissionDTO;
import com.lmbx.ssm.web.mapper.ModuleDao;
import com.lmbx.ssm.web.service.ModuleService;


/**
 * <pre>
 * 功能说明：
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleDao moduleDao;
	
	/* (non-Javadoc)
	 * @see com.bdcor.pip.web.sys.rbac.service.ModuleService#save(com.bdcor.pip.web.sys.rbac.domain.Module)
	 */
	@Override
	public String save(Module module) {
		if(checkExists(module.getName(), null)){
			//throw new ServiceException("模块名称已存在");
		}
		module.setCreateDatetime(new Date());
		module.setIsDelete(0);
		Integer indexNo = moduleDao.queryMaxIndex();
		if(null == indexNo){
			indexNo = 0;
		}
		module.setIndexNo(indexNo);
            return module.getId();
       
	}

	/* (non-Javadoc)
	 * @see com.bdcor.pip.web.sys.rbac.service.ModuleService#queryAll()
	 */
	@Override
	public List<Module> getAll() {
            return moduleDao.queryAll();
        
	}

	/* (non-Javadoc)
	 * @see com.bdcor.pip.web.sys.rbac.service.ModuleService#update(com.bdcor.pip.web.sys.rbac.domain.Module)
	 */
	@Override
	public String update(Module module) {
		if(checkExists(module.getName(), module.getId())){
			//throw new ServiceException("模块名已存在");
		}
		module.setUpdateDatetime(new Date());
		//module.setUpdateUser(Securitys.getUserId());
		try {
            moduleDao.update(module);
            //logService.info(Consts.SystemModel.SYS, "更新模块信息");
            return module.getId();
        } catch (Exception e) {
            //logService.error(Consts.SystemModel.SYS, "更新模块信息失败");
            //throw new ServiceException("更新模块信息失败",e);
        }
		return null;

	}

	/* (non-Javadoc)
	 * @see com.bdcor.pip.web.sys.rbac.service.ModuleService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		try{
		    moduleDao.delete(id);
		   // logService.warning(Consts.SystemModel.SYS, "删除模块");
		}catch(Exception e){
		   // logService.error(Consts.SystemModel.SYS, "删除模块失败");
		   // throw new ServiceException("模块删除失败 id="+id,e);
		}
	}
	

	/*
	 * (non-Javadoc)
	 * @see com.bdcor.pip.web.sys.rbac.service.ModuleService#getById(java.lang.String)
	 */
	@Override
	public Module getById(String id) {
		    return moduleDao.queryById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bdcor.pip.web.sys.rbac.service.ModuleService#deleteByIds(java.util.List)
	 */
    @Override
    public void deleteByIds(String[] ids) {
            moduleDao.deleteByIds(ids);
    }

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.ModuleService#getByTenantId(java.lang.String)
     */
    @Override
    public List<Module> getByOrganId(String organId) {
            return moduleDao.queryByOrganId(organId);
    }
    
    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.ModuleService#save(com.bdcor.pip.web.sys.rbac.domain.Module, java.lang.String[])
     */
	@Override
	public String save(Module module, String[] ids) {
		
		return saveOrUpdateModulePermission(module, ids, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bdcor.pip.web.sys.rbac.service.ModuleService#update(com.bdcor.pip.web.sys.rbac.domain.Module, java.lang.String[])
	 */
	@Override
	public String update(Module module, String[] ids) {
		
		return saveOrUpdateModulePermission(module, ids, true);
	}
    
    /**
     * 保存或更新模块权限
     * @param module
     * @param permissions
     * @param isUpdate
     * @return
     */
    private String saveOrUpdateModulePermission(Module module,String[] permissions, Boolean isUpdate) {
        
        String moduleId = null;
        
        //如果更新，删除该模块之前的权限
        if(null != isUpdate && isUpdate){
            //更新模块
            moduleId = update(module);
            
                moduleDao.deleteModulePermission(moduleId);
            
        }else{
            
            //保存模块
            moduleId = save(module);
        }
        
        if(null ==permissions || permissions.length==0){
            return moduleId;
        }
        
        List<ModulePermissionDTO> mpList = new ArrayList<ModulePermissionDTO>();
        
        ModulePermissionDTO rp = null;
        
        //构造模块权限集合
        for(String permissionId : permissions){
            
            rp = new ModulePermissionDTO(moduleId,permissionId);
            
            mpList.add(rp);
        }
        
        //更新模块权限
        if(!mpList.isEmpty()){
                moduleDao.saveModulePermissions(mpList);
        }
        return moduleId;
    }

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.ModuleService#checkExists(java.lang.String, int)
     */
	@Override
	public boolean checkExists(String name, String id) {
		return moduleDao.checkExists(name.trim(), id) > 0;
	}
}
