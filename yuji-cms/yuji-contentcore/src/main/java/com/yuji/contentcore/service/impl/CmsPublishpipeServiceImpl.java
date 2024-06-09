package com.yuji.contentcore.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.yuji.common.core.utils.Assert;
import com.yuji.common.core.utils.DateUtils;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.staticize.core.TemplateContext;
import com.yuji.contentcore.core.IContentType;
import com.yuji.contentcore.core.IPublishPipeProp;
import com.yuji.contentcore.core.impl.CatalogType_Link;
import com.yuji.contentcore.core.impl.PublishPipeProp_DefaultListTemplate;
import com.yuji.contentcore.domain.CmsCatalog;
import com.yuji.contentcore.domain.CmsContent;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.exception.ContentCoreErrorCode;
import com.yuji.contentcore.mapper.CmsSiteMapper;
import com.yuji.contentcore.service.ICmsTemplateService;
import com.yuji.contentcore.template.ITemplateType;
import com.yuji.contentcore.utils.ContentCoreUtils;
import com.yuji.contentcore.utils.SiteUtils;
import com.yuji.contentcore.utils.TemplateUtils;
import freemarker.template.TemplateException;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yuji.contentcore.mapper.CmsPublishpipeMapper;
import com.yuji.contentcore.domain.CmsPublishpipe;
import com.yuji.contentcore.service.ICmsPublishpipeService;

/**
 * 发布通道Service业务层处理
 * 
 * @author Liguoqiang
 * @date 2024-06-06
 */
@Service
public class CmsPublishpipeServiceImpl implements ICmsPublishpipeService 
{
    @Autowired
    private CmsPublishpipeMapper cmsPublishpipeMapper;
    @Autowired
    private List<IPublishPipeProp> publishPipeProps;



    /**
     * 查询发布通道
     * 
     * @param publishpipeId 发布通道主键
     * @return 发布通道
     */
    @Override
    public CmsPublishpipe selectCmsPublishpipeByPublishpipeId(Long publishpipeId)
    {
        return cmsPublishpipeMapper.selectCmsPublishpipeByPublishpipeId(publishpipeId);
    }

    /**
     * 查询发布通道列表
     * 
     * @param cmsPublishpipe 发布通道
     * @return 发布通道
     */
    @Override
    public List<CmsPublishpipe> selectCmsPublishpipeList(CmsPublishpipe cmsPublishpipe)
    {
        return cmsPublishpipeMapper.selectCmsPublishpipeList(cmsPublishpipe);
    }

    /**
     * 获取指定站点可用发布通道
     *
     * @param siteId 站点ID
     * @return 结果列表
     */
    @Override
    public List<CmsPublishpipe> getPublishPipes(Long siteId) {
        return cmsPublishpipeMapper.getPublishPipes(siteId);
    }

    /**
     * 新增发布通道
     * 
     * @param cmsPublishpipe 发布通道
     * @return 结果
     */
    @Override
    public int insertCmsPublishpipe(CmsPublishpipe cmsPublishpipe)
    {
        cmsPublishpipe.setCreateTime(DateUtils.getNowDate());
        return cmsPublishpipeMapper.insertCmsPublishpipe(cmsPublishpipe);
    }

    /**
     * 修改发布通道
     * 
     * @param cmsPublishpipe 发布通道
     * @return 结果
     */
    @Override
    public int updateCmsPublishpipe(CmsPublishpipe cmsPublishpipe)
    {
        cmsPublishpipe.setUpdateTime(DateUtils.getNowDate());
        return cmsPublishpipeMapper.updateCmsPublishpipe(cmsPublishpipe);
    }

    /**
     * 批量删除发布通道
     * 
     * @param publishpipeIds 需要删除的发布通道主键
     * @return 结果
     */
    @Override
    public int deleteCmsPublishpipeByPublishpipeIds(Long[] publishpipeIds)
    {
        return cmsPublishpipeMapper.deleteCmsPublishpipeByPublishpipeIds(publishpipeIds);
    }

    /**
     * 删除发布通道信息
     * 
     * @param publishpipeId 发布通道主键
     * @return 结果
     */
    @Override
    public int deleteCmsPublishpipeByPublishpipeId(Long publishpipeId)
    {
        return cmsPublishpipeMapper.deleteCmsPublishpipeByPublishpipeId(publishpipeId);
    }

    @Override
    public String getPublishPipePropValue(String propKey, String publishPipeCode,
                                          Map<String, Map<String, Object>> props) {
        String value;
        if (props != null) {
            value = MapUtils.getString(props.get(publishPipeCode), propKey);
            if (StringUtils.isNotEmpty(value)) {
                return value;
            }
        }
        Optional<IPublishPipeProp> opt = this.publishPipeProps.stream()
                .filter(p -> p.getKey().equals(propKey)).findFirst();
        if (opt.isPresent()) {
            return opt.get().getDefaultValue();
        }
        return null;
    }

}
