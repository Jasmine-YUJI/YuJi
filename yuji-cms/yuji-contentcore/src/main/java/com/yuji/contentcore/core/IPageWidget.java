package com.yuji.contentcore.core;

import com.yuji.contentcore.domain.CmsPageWidget;
import com.yuji.system.api.model.LoginUser;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * 页面部件
 *
 * @author Liguoqiang
 */
public interface IPageWidget {

	/**
	 * 页面部件类型ID
	 */
	default String getPageWidgetType() {
		return this.getPageWidgetEntity().getType();
	}
	
	/**
	 * 页面部件基础数据实例
	 */
	public CmsPageWidget getPageWidgetEntity();

	public void setPageWidgetEntity(CmsPageWidget cmsPageWdiget);

	/**
	 * 操作人
	 * 
	 * @param loginUser
	 */
	public void setOperator(LoginUser loginUser);

	public LoginUser getOperator();

	public void add();

	public void save();

	public void delete();

	public void publish() throws TemplateException, IOException;
}
