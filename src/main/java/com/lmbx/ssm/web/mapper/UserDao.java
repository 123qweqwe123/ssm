package com.lmbx.ssm.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.web.filter.authc.UserFilter;

import com.lmbx.ssm.utils.MyBatisRepository;
import com.lmbx.ssm.web.daomain.LccAccount;
import com.lmbx.ssm.web.daomain.User;
import com.lmbx.ssm.web.daomain.UserDTO;
import com.lmbx.ssm.web.daomain.UserDataLimit;
import com.lmbx.ssm.web.daomain.UserRoleDTO;


@MyBatisRepository
public interface UserDao{
    public User queryById(String id);

    public User queryByLoginName(@Param("loginName") String loginName);

    public List<User> queryUsersByLoginName(@Param("loginName") String loginName);
    
    public List<User> getListForLoginName(@Param("loginName") String loginName);
    
    List<UserDTO> list(UserFilter filter);

    public void save(User user);

    public void update(User user);
    
    void delete(String [] ids);

    public void saveUserRole(List<UserRoleDTO> urList);
    
    void deleteUserRole(String userId);
    
    void deleteUserRoles(String[] ids);
    /**
     * 新增账户和lcc关联信息
     * @param userLcc
     */
    public void saveLccAccount(LccAccount userLcc);
    /**
     * 修改账户和lcc关联信息
     * @param userLcc
     */
    public void updateLccAccount(LccAccount userLcc);
    /**
     * 删除账户和lcc关联信息
     * @param ids
     */
    public void deleteLccAccount(String[]ids);
    /**
     * 根据部门或公司获取人员
     * @param organId
     * @return
     */
    List<UserDTO> getUsersByOrganId(@Param("organId") String organId);

    List<UserDataLimit> getLccListBuUserId(UserDataLimit dataLimit);

    public List<Map<String, String>> getDataLimitByUserId(@Param("userId")String userId);
    
	public void deleteDataLimitByUserId(@Param("userId")String userId);
	
	public List<Map> exportAccount(@Param("userId")String userId);

	public List<UserDataLimit> getDataLimitTree(String userId);
}
