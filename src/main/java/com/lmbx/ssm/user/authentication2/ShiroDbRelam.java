/*package com.lmbx.ssm.user.authentication2;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.lmbx.ssm.utils.ShiroUser;
import com.lmbx.ssm.web.daomain.SysAccount;
import com.lmbx.ssm.web.service.UserService;

*//**
 * Description:
 * <pre>
 *
 * </pre>
 * Author: huangrupeng
 * Create: 17/5/16 下午12:59
 *//*
public class ShiroDbRelam extends AuthorizingRealm {

    @Autowired
    private SysAccountRoleMapper accountRoleMapper;

    @Autowired
    private SysRolePermissionMapper rolePermissionMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private SysPermissionMapper permissionMapper;

    @Autowired
    private SysMenuMapper menuMapper;


    *//**
     * 认证，
     *
     * @param token
     * @return
     * @throws AuthenticationException
     *//*
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        UserQO userQO = new UserQO(username, password);
        SysAccount account = userService.login(userQO);

        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setAccountId(account.getId());
        shiroUser.setAdmin(account.getIsAdmin() == 1);
        shiroUser.setName(account.getName());
        shiroUser.setLoginName(account.getLoginName());
        // 原系统 pip_sys_account 表的 id
        return new SimpleAuthenticationInfo(shiroUser,
                usernamePasswordToken.getPassword(), getName());
    }

    *//**
     * 授权，只有代码中第一次鉴权的时候才会触发当前方法的执行
     *
     * @param principals
     * @return
     *//*
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<SysRole> roles;
        List<SysPermission> permissions;
        List<SysMenu> menus;
        SysMenuExample menuExample = new SysMenuExample();
        menuExample.setOrderByClause("MENU_LEVEL, SEQUENCE asc");
        if (shiroUser.isAdmin()) {
            roles = roleMapper.selectByExample(null);
            permissions = permissionMapper.selectByExample(null);
            menus = menuMapper.selectByExample(menuExample);
        } else {
            SysAccountRoleExample sysAccountRoleExample = new SysAccountRoleExample();
            sysAccountRoleExample.createCriteria().andAccountIdEqualTo(shiroUser.getAccountId());
            List<SysAccountRoleKey> sysAccountRoleKeys = accountRoleMapper.selectByExample(sysAccountRoleExample);
            SysRoleExample roleExample = new SysRoleExample();
            List<String> roleIds = sysAccountRoleKeys.stream().map(SysAccountRoleKey::getRoleId).collect(Collectors.toList());
            roleExample.createCriteria().andIdIn(roleIds);
            roles = roleMapper.selectByExample(roleExample);

            SysRolePermissionExample rolePermissionExample = new SysRolePermissionExample();
            rolePermissionExample.createCriteria().andRoleIdIn(roleIds);
            List<SysRolePermissionKey> rolePermissionKeys = rolePermissionMapper.selectByExample(rolePermissionExample);
            List<String> permissionIds = rolePermissionKeys.stream().map(SysRolePermissionKey::getPermissionsId).collect(Collectors.toList());
            SysPermissionExample sysPermissionExample = new SysPermissionExample();
            sysPermissionExample.createCriteria().andIdIn(permissionIds);
            permissions = permissionMapper.selectByExample(sysPermissionExample);

            menuExample.createCriteria().andPermissionIn(permissionIds);
            menus = menuMapper.selectByExample(menuExample);
        }
        info.addRoles(roles.stream().map(SysRole::getName).collect(Collectors.toList()));
        info.addStringPermissions(permissions.stream().map(SysPermission::getCode).collect(Collectors.toList()));
        shiroUser.setMenus(menus);
        return info;
    }

}
*/