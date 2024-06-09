import request from '@/utils/request'

// 查询内容管理列表
export function listContent(query) {
  return request({
    url: '/cms/content/list',
    method: 'get',
    params: query
  })
}

// 查询内容管理详细
export function getContent(contentId) {
  return request({
    url: '/cms/content/' + contentId,
    method: 'get'
  })
}

// 新增内容管理
export function addContent(data) {
  return request({
    url: '/cms/content',
    method: 'post',
    data: data
  })
}

// 修改内容管理
export function updateContent(data) {
  return request({
    url: '/cms/content',
    method: 'put',
    data: data
  })
}

// 删除内容管理
export function delContent(contentId) {
  return request({
    url: '/cms/content/' + contentId,
    method: 'delete'
  })
}
