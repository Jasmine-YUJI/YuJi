import request from '@/utils/request'

// 查询页面部件列表
export function listPageWidget(query) {
  return request({
    url: '/cms/pageWidget/list',
    method: 'get',
    params: query
  })
}

// 查询页面部件详细
export function getPageWidget(pageWidgetId) {
  return request({
    url: '/cms/pageWidget/' + pageWidgetId,
    method: 'get'
  })
}

// 新增页面部件
export function addPageWidget(data) {
  return request({
    url: '/cms/pageWidget',
    method: 'post',
    data: data
  })
}

// 修改页面部件
export function updatePageWidget(data) {
  return request({
    url: '/cms/pageWidget',
    method: 'put',
    data: data
  })
}

// 删除页面部件
export function delPageWidget(pageWidgetId) {
  return request({
    url: '/cms/pageWidget/' + pageWidgetId,
    method: 'delete'
  })
}

export function listPageWidgetTypes() {
  return request({
    url: '/cms/pagewidget/types',
    method: 'get'
  })
}


export function listPageWidgets(params) {
  return request({
    url: '/cms/pagewidget',
    method: 'get',
    params: params
  })
}
