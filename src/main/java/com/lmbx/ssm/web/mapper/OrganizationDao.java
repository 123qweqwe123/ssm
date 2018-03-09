package com.lmbx.ssm.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lmbx.ssm.utils.MyBatisRepository;
import com.lmbx.ssm.web.daomain.OrganType;
import com.lmbx.ssm.web.daomain.Organization;


@MyBatisRepository
public interface OrganizationDao{

    void save(Organization organization);

    Organization queryById(String id);

    List<Organization> list(Map hashMap);

    void delete(List<String> idList);

    void update(Organization organization);

    Organization findByNameAndOrganId(@Param("name") String name,@Param("id") String id, @Param("organId") String organId);

	List<Organization> getOrgTree(Integer[] types);

	List<OrganType> getOrganType();

	Organization getDepartOrCompanyByUserId(@Param("organizationId") String organizationId, @Param("type") Integer type);
}
