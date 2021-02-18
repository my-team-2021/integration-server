package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.EquipmentInfoMapper;
import com.ruoyi.system.domain.EquipmentInfo;
import com.ruoyi.system.service.IEquipmentInfoService;

/**
 * 设备信息Service业务层处理
 * 
 * @author zengjl
 * @date 2021-02-18
 */
@Service
public class EquipmentInfoServiceImpl implements IEquipmentInfoService 
{
    @Autowired
    private EquipmentInfoMapper equipmentInfoMapper;

    /**
     * 查询设备信息
     * 
     * @param id 设备信息ID
     * @return 设备信息
     */
    @Override
    public EquipmentInfo selectEquipmentInfoById(Long id)
    {
        return equipmentInfoMapper.selectEquipmentInfoById(id);
    }

    /**
     * 查询设备信息列表
     * 
     * @param equipmentInfo 设备信息
     * @return 设备信息
     */
    @Override
    public List<EquipmentInfo> selectEquipmentInfoList(EquipmentInfo equipmentInfo)
    {
        return equipmentInfoMapper.selectEquipmentInfoList(equipmentInfo);
    }

    /**
     * 新增设备信息
     * 
     * @param equipmentInfo 设备信息
     * @return 结果
     */
    @Override
    public int insertEquipmentInfo(EquipmentInfo equipmentInfo)
    {
        equipmentInfo.setCreateTime(DateUtils.getNowDate());
        return equipmentInfoMapper.insertEquipmentInfo(equipmentInfo);
    }

    /**
     * 修改设备信息
     * 
     * @param equipmentInfo 设备信息
     * @return 结果
     */
    @Override
    public int updateEquipmentInfo(EquipmentInfo equipmentInfo)
    {
        equipmentInfo.setUpdateTime(DateUtils.getNowDate());
        return equipmentInfoMapper.updateEquipmentInfo(equipmentInfo);
    }

    /**
     * 批量删除设备信息
     * 
     * @param ids 需要删除的设备信息ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentInfoByIds(Long[] ids)
    {
        return equipmentInfoMapper.deleteEquipmentInfoByIds(ids);
    }

    /**
     * 删除设备信息信息
     * 
     * @param id 设备信息ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentInfoById(Long id)
    {
        return equipmentInfoMapper.deleteEquipmentInfoById(id);
    }
}
