import request from '@/utils/request'

// 查询关联内容列表
export function listRela(query) {
  return request({
    url: '/cms/rela/list',
    method: 'get',
    params: query
  })
}

// 查询关联内容详细
export function getRela(relaId) {
  return request({
    url: '/cms/rela/' + relaId,
    method: 'get'
  })
}

// 新增关联内容
export function addRela(data) {
  return request({
    url: '/cms/rela',
    method: 'post',
    data: data
  })
}

// 修改关联内容
export function updateRela(data) {
  return request({
    url: '/cms/rela',
    method: 'put',
    data: data
  })
}

// 删除关联内容
export function delRela(relaId) {
  return request({
    url: '/cms/rela/' + relaId,
    method: 'delete'
  })
}
