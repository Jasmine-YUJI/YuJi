package com.yuji.contentcore.core;


import com.yuji.contentcore.domain.CmsPageWidget;
import com.yuji.contentcore.domain.dto.PageWidgetAddDTO;
import com.yuji.contentcore.domain.dto.PageWidgetEditDTO;
import com.yuji.contentcore.domain.vo.PageWidgetVO;

/**
 * 页面部件类型
 *
 * @author Liguoqiang
 */
public interface IPageWidgetType {
	
	/**
	 * Bean名称前缀
	 */
	String BEAN_NAME_PREFIX = "PageWidgetType_";

	/**
	 * 唯一标识：类型Id
	 */
	String getId();
	
	/**
	 * 类型名
	 */
	String getName();
	
	/**
	 * 类型图标
	 */
	String getIcon();
	
	/**
	 * 获取路由地址
	 */
	String getRoute();

	/**
	 * 加载部件数据详情
	 * 
	 * @param pageWidget
	 * @return
	 */
	IPageWidget loadPageWidget(CmsPageWidget pageWidget);

	
	default Class<?> getAddDTOClass() {
		return PageWidgetAddDTO.class;
	}

	default Class<?> getEditDTOClass() {
		return PageWidgetEditDTO.class;
	}

	/**
	 * 页面部件数据转VO
	 * 
	 * @param pageWidget
	 * @return
	 */
	PageWidgetVO getPageWidgetVO(CmsPageWidget pageWidget);

	/**
	 * 创建页面部件实例
	 * 
	 * @return
	 */
	IPageWidget newInstance();

	/**
	 * 获取模板页面部件内容
	 * 
	 * @param pageWidget
	 * @param isPreview
	 * @return
	 */
	Object parseContent(CmsPageWidget pageWidget, String publishPipeCode, boolean isPreview);
}
