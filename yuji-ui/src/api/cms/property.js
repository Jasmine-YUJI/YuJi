import request from '@/utils/request'

// 查询站点自定义属性列表
export function listProperty(query) {
  return request({
    url: '/cms/property/list',
    method: 'get',
    params: query
  })
}

// 查询站点自定义属性详细
export function getProperty(propertyId) {
  return request({
    url: '/cms/property/' + propertyId,
    method: 'get'
  })
}

// 新增站点自定义属性
export function addProperty(data) {
  return request({
    url: '/cms/property',
    method: 'post',
    data: data
  })
}

// 修改站点自定义属性
export function updateProperty(data) {
  return request({
    url: '/cms/property',
    method: 'put',
    data: data
  })
}

// 删除站点自定义属性
export function delProperty(propertyId) {
  return request({
    url: '/cms/property/' + propertyId,
    method: 'delete'
  })
}
