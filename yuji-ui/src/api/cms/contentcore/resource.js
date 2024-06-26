import request from '@/utils/request'

export function getResourceTypes() {
  return request({
    url: '/cms/resource/types',
    method: 'get'
  })
}

// 查询资源列表
export function getResrouceList(query) {
  return request({
    url: '/cms/resource/list',
    method: 'get',
    params: query
  })
}

// 查询资源详细
export function getResourceDetail(resourceId) {
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
    data: data,
    headers: {
      "Content-type": "multipart/form-data"
    }
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
