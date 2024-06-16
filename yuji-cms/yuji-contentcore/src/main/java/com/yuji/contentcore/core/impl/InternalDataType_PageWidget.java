package com.yuji.contentcore.core.impl;

import com.yuji.common.core.exception.CommonErrorCode;
import com.yuji.common.core.utils.Assert;
import com.yuji.contentcore.core.IInternalDataType;
import com.yuji.contentcore.domain.CmsPageWidget;
import com.yuji.contentcore.service.ICmsPageWidgetService;
import com.yuji.contentcore.service.IPublishService;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 内部数据类型：页面组件
 *
 * @author Liguqoiang
 */
@RequiredArgsConstructor
@Component(IInternalDataType.BEAN_NAME_PREFIX + InternalDataType_PageWidget.ID)
public class InternalDataType_PageWidget implements IInternalDataType {

	public final static String ID = "pagewidget";
	
	private final ICmsPageWidgetService pageWidgetService;
	
	private final IPublishService publishService;
	
	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String getPageData(RequestData data) throws IOException, TemplateException {
		CmsPageWidget pageWidget = pageWidgetService.selectCmsPageWidgetByPageWidgetId(data.getDataId());
		Assert.notNull(pageWidget, () -> CommonErrorCode.DATA_NOT_FOUND_BY_ID.exception("pageWidgetId", data.getDataId()));
		
		return this.publishService.getPageWidgetPageData(pageWidget, data.isPreview());
	}
}
