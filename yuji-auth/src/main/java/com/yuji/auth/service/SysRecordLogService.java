package com.yuji.auth.service;

import com.yuji.common.core.utils.IP2RegionUtils;
import com.yuji.common.core.utils.ServletUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.yuji.common.core.constant.Constants;
import com.yuji.common.core.constant.SecurityConstants;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.core.utils.ip.IpUtils;
import com.yuji.system.api.RemoteLogService;
import com.yuji.system.api.domain.SysLogininfor;

/**
 * 记录日志方法
 * 
 * @author Liguoqiang
 */
@Component
public class SysRecordLogService
{
    @Autowired
    private RemoteLogService remoteLogService;

    /**
     * 记录登录信息
     * 
     * @param username 用户名
     * @param status 状态
     * @param message 消息内容
     * @return
     */
    public void recordLogininfor(String username, String status, String message)
    {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getUserAgent());
        // 获取客户端操作系统
        String os = userAgent.getOperatingSystem().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();
        SysLogininfor logininfor = new SysLogininfor();
        logininfor.setUserName(username);
        logininfor.setIpaddr(IpUtils.getIpAddr());
        logininfor.setLoginLocation(IP2RegionUtils.ip2Region(IpUtils.getIpAddr()));
        logininfor.setBrowser(browser);
        logininfor.setOs(os);
        logininfor.setMsg(message);
        // 日志状态
        if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER))
        {
            logininfor.setStatus(Constants.LOGIN_SUCCESS_STATUS);
        }
        else if (Constants.LOGIN_FAIL.equals(status))
        {
            logininfor.setStatus(Constants.LOGIN_FAIL_STATUS);
        }
        remoteLogService.saveLogininfor(logininfor, SecurityConstants.INNER);
    }
}
