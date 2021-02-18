package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.EquipmentInfo;

/**
 * 设备信息Mapper接口
 * 
 * @author zengjl
 * @date 2021-02-18
 */
public interface EquipmentInfoMapper 
{
    /**
     * 查询设备信息
     * 
     * @param id 设备信息ID
     * @return 设备信息
     */
    public EquipmentInfo selectEquipmentInfoById(Long id);

    /**
     * 查询设备信息列表
     * 
     * @param equipmentInfo 设备信息
     * @return 设备信息集合
     */
    public List<EquipmentInfo> selectEquipmentInfoList(EquipmentInfo equipmentInfo);

    /**
     * 新增设备信息
     * 
     * @param equipmentInfo 设备信息
     * @return 结果
     */
    public int insertEquipmentInfo(EquipmentInfo equipmentInfo);

    /**
     * 修改设备信息
     * 
     * @param equipmentInfo 设备信息
     * @return 结果
     */
    public int updateEquipmentInfo(EquipmentInfo equipmentInfo);

    /**
     * 删除设备信息
     * 
     * @param id 设备信息ID
     * @return 结果
     */
    public int deleteEquipmentInfoById(Long id);

    /**
     * 批量删除设备信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEquipmentInfoByIds(Long[] ids);
}
