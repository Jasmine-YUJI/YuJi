import request from '@/utils/request'

// 查询栏目管理列表
export function listCatalog(query) {
  return request({
    url: '/cms/catalog/list',
    method: 'get',
    params: query
  })
}

// 查询栏目管理详细
export function getCatalog(catalogId) {
  return request({
    url: '/cms/catalog/' + catalogId,
    method: 'get'
  })
}

// 新增栏目管理
export function addCatalog(data) {
  return request({
    url: '/cms/catalog',
    method: 'post',
    data: data
  })
}

// 修改栏目管理
export function updateCatalog(data) {
  return request({
    url: '/cms/catalog',
    method: 'put',
    data: data
  })
}

// 删除栏目管理
export function delCatalog(catalogId) {
  return request({
    url: '/cms/catalog/' + catalogId,
    method: 'delete'
  })
}
