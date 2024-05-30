package com.yuji.auth.controller;

import javax.servlet.http.HttpServletRequest;

import com.yuji.common.core.utils.sign.RsaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.yuji.auth.form.LoginBody;
import com.yuji.auth.form.RegisterBody;
import com.yuji.auth.service.SysLoginService;
import com.yuji.common.core.domain.R;
import com.yuji.common.core.utils.JwtUtils;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.security.auth.AuthUtil;
import com.yuji.common.security.service.TokenService;
import com.yuji.common.security.utils.SecurityUtils;
import com.yuji.system.api.model.LoginUser;

/**
 * token 控制
 * 
 * @author Liguoqiang
 */
@RestController
public class TokenController
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form) throws Exception {
        // 用户登录
        String aa = RsaUtils.decryptByPrivateKey(form.getPassword());
        LoginUser userInfo = sysLoginService.login(form.getUsername(), RsaUtils.decryptByPrivateKey(form.getPassword()));
        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }

    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request)
    {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }

    @PostMapping("register")
    public R<?> register(@RequestBody RegisterBody registerBody)
    {
        // 用户注册
        sysLoginService.register(registerBody.getUsername(), registerBody.getPassword());
        return R.ok();
    }
}