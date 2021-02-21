package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.system.domain.DeviceInfo;
import com.ruoyi.system.mapper.DeviceInfoMapper;
import com.ruoyi.system.service.IDeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 设备信息维护Service业务层处理
 * 
 * @author zengjl
 * @date 2021-02-19
 */
@Service
public class DeviceInfoServiceImpl implements IDeviceInfoService
{
    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    /**
     * 查询设备信息维护
     * 
     * @param id 设备信息维护ID
     * @return 设备信息维护
     */
    @Override
    public DeviceInfo selectDeviceInfoById(Long id)
    {
        return deviceInfoMapper.selectDeviceInfoById(id);
    }

    /**
     * 查询设备信息维护列表
     * 
     * @param deviceInfo 设备信息维护
     * @return 设备信息维护
     */
    @Override
    public List<DeviceInfo> selectDeviceInfoList(DeviceInfo deviceInfo)
    {
        return deviceInfoMapper.selectDeviceInfoList(deviceInfo);
    }

    /**
     * 新增设备信息维护
     * 
     * @param deviceInfo 设备信息维护
     * @return 结果
     */
    @Override
    public int insertDeviceInfo(DeviceInfo deviceInfo)
    {
        deviceInfo.setCreateTime(DateUtils.getNowDate());
        return deviceInfoMapper.insertDeviceInfo(deviceInfo);
    }

    /**
     * 修改设备信息维护
     * 
     * @param deviceInfo 设备信息维护
     * @return 结果
     */
    @Override
    public int updateDeviceInfo(DeviceInfo deviceInfo)
    {
        deviceInfo.setUpdateTime(DateUtils.getNowDate());
        return deviceInfoMapper.updateDeviceInfo(deviceInfo);
    }

    /**
     * 批量删除设备信息维护
     * 
     * @param ids 需要删除的设备信息维护ID
     * @return 结果
     */
    @Override
    public int deleteDeviceInfoByIds(Long[] ids)
    {
        return deviceInfoMapper.deleteDeviceInfoByIds(ids);
    }

    /**
     * 删除设备信息维护信息
     * 
     * @param id 设备信息维护ID
     * @return 结果
     */
    @Override
    public int deleteDeviceInfoById(Long id)
    {
        return deviceInfoMapper.deleteDeviceInfoById(id);
    }

    /**
     * 查询检修人员所属的设备信息
     *
     * @param userId 用户ID
     * @return 设备信息维护
     */
    @Override
    public List<DeviceInfo> selectCheckEquipsOfUser(String userId) {
        return deviceInfoMapper.selectValidDevicesByUserId(userId);
    }
}
