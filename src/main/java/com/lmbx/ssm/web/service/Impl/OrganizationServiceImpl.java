package com.lmbx.ssm.web.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmbx.ssm.web.daomain.OrganType;
import com.lmbx.ssm.web.daomain.Organization;
import com.lmbx.ssm.web.daomain.User;
import com.lmbx.ssm.web.mapper.OrganizationDao;
import com.lmbx.ssm.web.mapper.UserDao;
import com.lmbx.ssm.web.service.OrganizationService;


@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService{

    private static Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    @Autowired
    private OrganizationDao organizationDao;
    
    @Autowired
    private UserDao userDao;
    

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.OrganizationService#queryById(java.lang.String)
     */
    @Override
    public Organization getById(String id) {
            return organizationDao.queryById(id);
    }

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.OrganizationService#list()
     */
    @Override
    public List<Organization> list(Map condit) {
            return organizationDao.list(condit);
    }

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.OrganizationService#save(com.bdcor.pip.web.sys.rbac.domain.Organization)
     */
    @Override
    public String save(Organization organization) {
		return null;
    }

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.OrganizationService#delete(java.util.List)
     */
    @Override
    public void delete(List<String> idList) {
        idList.remove("");
        idList.remove(null);
        organizationDao.delete(idList);

    }

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.OrganizationService#update(com.bdcor.pip.web.sys.rbac.domain.Organization)
     */
    @Override
    public void update(Organization organization) {

    }

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.OrganizationService#rename(java.lang.String, java.lang.String)
     */
    @Override
    public void rename(String id, String newName) {
           // update(org);
    }
    
    public boolean checkExists(String name,String id){
    	//Organization oldOrganization = organizationDao.findByNameAndOrganId(name,id, Securitys.getOrganId());
        /*if (null != oldOrganization) {
            return true;
        }*/
        return false;
    }

	@Override
	public List<Organization> getOrgTree(Integer[] types) {
		return organizationDao.getOrgTree(types);
	}

	@Override
	public List<OrganType> getOrganType() {
		return organizationDao.getOrganType();
	}

	@Override
	public Organization getDepartOrCompanyByUserId(String userId, Integer type) {
		User user = userDao.queryById(userId);
		Organization org = organizationDao.getDepartOrCompanyByUserId(user.getOrganizationId(), type);
		return org;
	}

    
}
