package com.lmbx.ssm.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lmbx.ssm.utils.MyBatisRepository;
import com.lmbx.ssm.web.daomain.Menu;


@MyBatisRepository
public interface MenuDao {

	List<Menu> queryAllMenus();
	
	void save(Menu menu);
	
	void update(Menu menu);
	
	void delete(String id);

	 public List getMenuByUserPermission(@Param("userId") String userId);

	List getMenuById(String id);

	void updateMenuOder(@Param("id") String id, @Param("menuOrder") String menuOrder);

	Integer getOrderCount(@Param("parentId") String parentId);

	Menu checkNameExists(Menu menu);  
	
}
