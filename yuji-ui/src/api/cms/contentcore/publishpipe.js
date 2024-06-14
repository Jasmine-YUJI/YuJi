import request from '@/utils/request'

// 查询发布通道列表
export function listPublishpipe(query) {
  return request({
    url: '/cms/publishpipe/list',
    method: 'get',
    params: query
  })
}

// 查询发布通道详细
export function getPublishpipe(publishpipeId) {
  return request({
    url: '/cms/publishpipe/' + publishpipeId,
    method: 'get'
  })
}


// 新增发布通道
export function addPublishpipe(data) {
  return request({
    url: '/cms/publishpipe',
    method: 'post',
    data: data
  })
}

// 修改发布通道
export function updatePublishpipe(data) {
  return request({
    url: '/cms/publishpipe',
    method: 'put',
    data: data
  })
}

// 删除发布通道
export function delPublishpipe(publishpipeId) {
  return request({
    url: '/cms/publishpipe/' + publishpipeId,
    method: 'delete'
  })
}

// 查询发布通道列表
export function getPublishPipeSelectData(query) {
  return request({
    url: '/cms/publishpipe/selectData',
    method: 'get',
    params: query
  })
}


