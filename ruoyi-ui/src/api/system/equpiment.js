import request from '@/utils/request'

// 查询设备信息列表
export function listEqupiment(query) {
  return request({
    url: '/system/equpiment/list',
    method: 'get',
    params: query
  })
}

// 查询设备信息详细
export function getEqupiment(id) {
  return request({
    url: '/system/equpiment/' + id,
    method: 'get'
  })
}

// 查询设备二维码信息
export function getEqupimentQrCode(id) {
  return request({
    url: '/system/equpiment/qrCodeExport/' + id,
    method: 'get'
  })
}

// 新增设备信息
export function addEqupiment(data) {
  return request({
    url: '/system/equpiment',
    method: 'post',
    data: data
  })
}

// 修改设备信息
export function updateEqupiment(data) {
  return request({
    url: '/system/equpiment',
    method: 'put',
    data: data
  })
}

// 删除设备信息
export function delEqupiment(id) {
  return request({
    url: '/system/equpiment/' + id,
    method: 'delete'
  })
}
