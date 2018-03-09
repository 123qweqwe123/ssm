package com.lmbx.ssm.user.authentication2;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.lmbx.ssm.utils.SpringContextHolder;
import com.lmbx.ssm.web.service.UserService;

/**
 * Description:
 * <pre>
 *     自定义 authc 的 filter，1.添加登录日志 2.初始化授权行为
 * </pre>
 * Author: huangrupeng
 * Create: 17/6/5 上午8:31
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }
        try {
            Subject subject = getSubject(request, response);
            subject.login(token);
            // 创建登陆日志
            createLoginLog(request);
            // 执行授权操作
            subject.isPermitted("aa");
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            //throw e;
            return onLoginFailure(token, e, request, response);
        }
    }

    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        //we handled the success redirect directly, prevent the chain from continuing:
        return false;
    }

    private void createLoginLog(ServletRequest request) {
        UserService userService = SpringContextHolder.getBean(UserService.class);
        //userService.createLoginLog(request);
    }
}
