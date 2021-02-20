import request from '@/utils/request'

// 查询检修操作记录列表
export function listCheckLog(query) {
  return request({
    url: '/system/checkLog/list',
    method: 'get',
    params: query
  })
}

// 查询检修操作记录详细
export function getCheckLog(id) {
  return request({
    url: '/system/checkLog/' + id,
    method: 'get'
  })
}

// 新增检修操作记录
export function addCheckLog(data) {
  return request({
    url: '/system/checkLog',
    method: 'post',
    data: data
  })
}

// 修改检修操作记录
export function updateCheckLog(data) {
  return request({
    url: '/system/checkLog',
    method: 'put',
    data: data
  })
}

// 删除检修操作记录
export function delCheckLog(id) {
  return request({
    url: '/system/checkLog/' + id,
    method: 'delete'
  })
}
