import request from '@/utils/request'

// 查询设备信息维护列表
export function listDevice(query) {
  return request({
    url: '/system/device/list',
    method: 'get',
    params: query
  })
}

// 查询设备信息维护详细
export function getDevice(id) {
  return request({
    url: '/system/device/' + id,
    method: 'get'
  })
}
// 查询设备二维码信息
export function getDeviceQrCode(id) {
  return request({
    url: '/system/device/qrCodeExport/' + id,
    method: 'get'
  })
}

// 新增设备信息维护
export function addDevice(data) {
  return request({
    url: '/system/device',
    method: 'post',
    data: data
  })
}

// 修改设备信息维护
export function updateDevice(data) {
  return request({
    url: '/system/device',
    method: 'put',
    data: data
  })
}

// 删除设备信息维护
export function delDevice(id) {
  return request({
    url: '/system/device/' + id,
    method: 'delete'
  })
}
