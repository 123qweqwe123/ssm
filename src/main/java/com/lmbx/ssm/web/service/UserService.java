package com.lmbx.ssm.web.service;

import java.util.List;
import java.util.Map;

import org.apache.shiro.web.filter.authc.UserFilter;

import com.lmbx.ssm.web.daomain.User;
import com.lmbx.ssm.web.daomain.UserDTO;
import com.lmbx.ssm.web.daomain.UserDataLimit;


public interface UserService{

    /**
     * 
     * @param id
     * @return
     */
    public User getById(String id);

    /**
     * 根据登录名和租户id查询用户信息
     * @param loginName
     * @param tenantId
     * @return
     */
    public User findUserByLoginName(String loginName);
    
    public List<User> queryUsersByLoginName(String loginName);

    /**
     * 查询用户列表
     * @param UserFilter
     * @return
     */
    public List<UserDTO> list(UserFilter filter);
    
    public List<UserDTO> list(String id);
    /**
     * 新增用户
     * @param user
     * @return
     */
    public String save(User user);

    /**
     * 新增用户信息和角色信息
     * @param user
     * @param roles
     */
    public String save(User user,String[] roles)throws Exception;
    
    
    /**
     * 登陆逻辑
     * @param loginName 登陆名
     * @param password 密码
     * @param tenantName 租户名
     * @return
     */
    public User login(String loginName, String password) throws Exception;
    
    /**
     * 更新用户信息
     * @param user
     */
    public String update (User user)throws Exception;

    /**
     * 更新用户和角色信息
     * @param user
     * @param roles
     */
    public String update(User user,String[] roles)throws Exception;
    
    /**
     * 批量删除用户
     * @param ids
     */
    public void delete(String[] ids);
    
    /**
     * 重置用户密码
     * @param id
     * @param newPassword
     */
    public void resetPassword(String id, String newPassword);
    
    /**
     * 检查用户名是否存在
     * @param loginName
     * @param id
     * @return
     */
    public Boolean chekcLoginNameExists(String loginName, String id); 
    
    /**
     * 检查旧密码是否正确
     * @param userId
     * @param password
     * @return
     */
    public Boolean checkConfirmOldPassword(String userId,String password) throws Exception;

    /**
     * 修改密码
     * @param currentPassword
     * @param newPassword
     * @param id
     */
    public void modifyPassword(String currentPassword, String newPassword,String id) throws Exception;
    
    
    /**
     * 保存或更新用户角色
     * @param user
     * @param roles
     * @param isUpdate 是否更新
     * @return 用户id
     */
    public String saveOrUpdateUserRole(User user,String[] roles, Boolean isUpdate) throws Exception;
    
    public List<UserDTO> getUsersByOrganId(String organId);

	public List<UserDataLimit> getUserDataLimit(UserDataLimit ud);

	public void updataLccLimit(String id, String destLcc);
	
	public String getUserDataLimitSQL(String userId);
	
	public List<Map> exportExcelSQL(String userId);
	
	public  String getListForLoginName(String loginName);

	public List<String> getUserDatas(String userId);

	public List<UserDataLimit> getDataLimitTree();
	
}
