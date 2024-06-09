package com.yuji.contentcore.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.yuji.common.core.utils.Assert;
import com.yuji.common.core.utils.ConvertUtils;
import com.yuji.common.core.utils.DateUtils;
import com.yuji.common.core.utils.ServletUtils;
import com.yuji.common.core.utils.uuid.IdUtils;
import com.yuji.common.security.utils.SecurityUtils;
import com.yuji.contentcore.ContentCoreConsts;
import com.yuji.contentcore.exception.ContentCoreErrorCode;
import com.yuji.contentcore.perm.SitePermissionType.SitePrivItem;
import com.yuji.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yuji.contentcore.mapper.CmsSiteMapper;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.service.ICmsSiteService;

import javax.servlet.http.HttpServletRequest;

/**
 * 站点管理Service业务层处理
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@Service
public class CmsSiteServiceImpl implements ICmsSiteService 
{
    @Autowired
    private CmsSiteMapper cmsSiteMapper;

    /**
     * 查询站点管理
     * 
     * @param siteId 站点管理主键
     * @return 站点管理
     */
    @Override
    public CmsSite selectCmsSiteBySiteId(Long siteId)
    {
        return cmsSiteMapper.selectCmsSiteBySiteId(siteId);
    }

    /**
     * 获取当前站点，保存在token中
     */
    @Override
    public CmsSite getCurrentSite(HttpServletRequest request) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        CmsSite site = null;
        Long siteId = ConvertUtils.toLong(ServletUtils.getHeader(request, ContentCoreConsts.Header_CurrentSite));
        if (IdUtils.validate(siteId)
                && SecurityUtils.hasPermission(SitePrivItem.View.getPermissionKey(siteId),loginUser.getUserid())) {
            site = this.selectCmsSiteBySiteId(siteId);
        }
        // 无当前站点或当前站点无权限则取数据库查找一条有权限的站点数据作为当前站点
        if (Objects.isNull(site)) {

            List<CmsSite> list = cmsSiteMapper.selectCmsSiteList(new CmsSite());
            Optional<CmsSite> opt = list.stream().filter(s ->
                    SecurityUtils.hasPermission(SitePrivItem.View.getPermissionKey(s.getSiteId()),loginUser.getUserid())).findFirst();
            if (opt.isPresent()) {
                site = opt.get();
            }
        }
        Assert.notNull(site, ContentCoreErrorCode.NO_SITE::exception);
        return site;
    }

    /**
     * 查询站点管理列表
     * 
     * @param cmsSite 站点管理
     * @return 站点管理
     */
    @Override
    public List<CmsSite> selectCmsSiteList(CmsSite cmsSite)
    {
        return cmsSiteMapper.selectCmsSiteList(cmsSite);
    }

    /**
     * 新增站点管理
     * 
     * @param cmsSite 站点管理
     * @return 结果
     */
    @Override
    public int insertCmsSite(CmsSite cmsSite)
    {
        cmsSite.setCreateTime(DateUtils.getNowDate());
        return cmsSiteMapper.insertCmsSite(cmsSite);
    }

    /**
     * 修改站点管理
     * 
     * @param cmsSite 站点管理
     * @return 结果
     */
    @Override
    public int updateCmsSite(CmsSite cmsSite)
    {
        cmsSite.setUpdateTime(DateUtils.getNowDate());
        return cmsSiteMapper.updateCmsSite(cmsSite);
    }

    /**
     * 批量删除站点管理
     * 
     * @param siteIds 需要删除的站点管理主键
     * @return 结果
     */
    @Override
    public int deleteCmsSiteBySiteIds(Long[] siteIds)
    {
        return cmsSiteMapper.deleteCmsSiteBySiteIds(siteIds);
    }

    /**
     * 删除站点管理信息
     * 
     * @param siteId 站点管理主键
     * @return 结果
     */
    @Override
    public int deleteCmsSiteBySiteId(Long siteId)
    {
        return cmsSiteMapper.deleteCmsSiteBySiteId(siteId);
    }
}
