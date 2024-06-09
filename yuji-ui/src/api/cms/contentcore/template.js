import request from '@/utils/request'

// 查询模板管理列表
export function getTemplateList(query) {
  return request({
    url: '/cms/template/list',
    method: 'get',
    params: query
  })
}

// 查询模板管理详细
export function getTemplateDetail(templateId) {
  return request({
    url: '/cms/template/' + templateId,
    method: 'get'
  })
}

// 新增模板管理
export function addTemplate(data) {
  return request({
    url: '/cms/template',
    method: 'post',
    data: data
  })
}

// 修改模板管理
export function editTemplate(data) {
  return request({
    url: '/cms/template',
    method: 'put',
    data: data
  })
}

// 删除模板管理
export function delTemplate(templateId) {
  return request({
    url: '/cms/template/' + templateId,
    method: 'delete'
  })
}

// 模板文件重命名
export function renameTemplate(data) {
  return request({
    url: '/cms/template/rename',
    method: 'post',
    data: data
  })
}

// 删除区块模板缓存
export function clearIncludeCache(templates) {
  return request({
    url: '/cms/template/clearIncludeCache',
    method: 'delete',
    data: templates
  })
}
