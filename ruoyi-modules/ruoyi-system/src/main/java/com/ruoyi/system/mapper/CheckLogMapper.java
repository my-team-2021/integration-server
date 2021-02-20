package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.CheckItemDetail;
import com.ruoyi.system.domain.CheckLog;

import java.util.List;

/**
 * 检修操作记录Mapper接口
 * 
 * @author zengjl
 * @date 2021-02-19
 */
public interface CheckLogMapper 
{
    /**
     * 查询检修操作记录
     * 
     * @param id 检修操作记录ID
     * @return 检修操作记录
     */
    public CheckLog selectCheckLogById(Long id);

    /**
     * 查询检修操作记录列表
     * 
     * @param checkLog 检修操作记录
     * @return 检修操作记录集合
     */
    public List<CheckLog> selectCheckLogList(CheckLog checkLog);

    /**
     * 新增检修操作记录
     * 
     * @param checkLog 检修操作记录
     * @return 结果
     */
    public int insertCheckLog(CheckLog checkLog);

    /**
     * 修改检修操作记录
     * 
     * @param checkLog 检修操作记录
     * @return 结果
     */
    public int updateCheckLog(CheckLog checkLog);

    /**
     * 删除检修操作记录
     * 
     * @param id 检修操作记录ID
     * @return 结果
     */
    public int deleteCheckLogById(Long id);

    /**
     * 批量删除检修操作记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCheckLogByIds(Long[] ids);

    /**
     * 批量删除检修项详情
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCheckItemDetailByVersions(Long[] ids);
    
    /**
     * 批量新增检修项详情
     * 
     * @param checkItemDetailList 检修项详情列表
     * @return 结果
     */
    public int batchCheckItemDetail(List<CheckItemDetail> checkItemDetailList);
    

    /**
     * 通过检修操作记录ID删除检修项详情信息
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteCheckItemDetailByVersion(Long id);
}
