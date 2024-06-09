import request from '@/utils/request'

// 查询资源列表
export function listResource(query) {
  return request({
    url: '/cms/resource/list',
    method: 'get',
    params: query
  })
}

// 查询资源详细
export function getResource(resourceId) {
  return request({
    url: '/cms/resource/' + resourceId,
    method: 'get'
  })
}

// 新增资源
export function addResource(data) {
  return request({
    url: '/cms/resource',
    method: 'post',
    data: data
  })
}

// 修改资源
export function updateResource(data) {
  return request({
    url: '/cms/resource',
    method: 'put',
    data: data
  })
}

// 删除资源
export function delResource(resourceId) {
  return request({
    url: '/cms/resource/' + resourceId,
    method: 'delete'
  })
}
