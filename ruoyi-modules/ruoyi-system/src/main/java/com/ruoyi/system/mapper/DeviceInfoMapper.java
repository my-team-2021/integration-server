package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.DeviceInfo;

import java.util.List;

/**
 * 设备信息维护Mapper接口
 * 
 * @author zengjl
 * @date 2021-02-19
 */
public interface DeviceInfoMapper 
{
    /**
     * 查询设备信息维护
     * 
     * @param id 设备信息维护ID
     * @return 设备信息维护
     */
    public DeviceInfo selectDeviceInfoById(Long id);

    /**
     * 查询设备信息维护列表
     * 
     * @param deviceInfo 设备信息维护
     * @return 设备信息维护集合
     */
    public List<DeviceInfo> selectDeviceInfoList(DeviceInfo deviceInfo);

    /**
     * 新增设备信息维护
     * 
     * @param deviceInfo 设备信息维护
     * @return 结果
     */
    public int insertDeviceInfo(DeviceInfo deviceInfo);

    /**
     * 修改设备信息维护
     * 
     * @param deviceInfo 设备信息维护
     * @return 结果
     */
    public int updateDeviceInfo(DeviceInfo deviceInfo);

    /**
     * 删除设备信息维护
     * 
     * @param id 设备信息维护ID
     * @return 结果
     */
    public int deleteDeviceInfoById(Long id);

    /**
     * 批量删除设备信息维护
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDeviceInfoByIds(Long[] ids);

    /**
     * 查询某检修人员所属的检修设备信息
     *
     * @param userId 用户id
     * @return 设备信息维护集合
     */
    public List<DeviceInfo> selectValidDevicesByUserId(Long userId);
}
