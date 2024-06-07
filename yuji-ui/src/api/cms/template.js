import request from '@/utils/request'

// 查询模板管理列表
export function listTemplate(query) {
  return request({
    url: '/cms/template/list',
    method: 'get',
    params: query
  })
}

// 查询模板管理详细
export function getTemplate(templateId) {
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
export function updateTemplate(data) {
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
