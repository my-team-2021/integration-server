package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CheckLog;

/**
 * 检修操作记录Service接口
 * 
 * @author zengjl
 * @date 2021-02-20
 */
public interface ICheckLogService 
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
     * 批量删除检修操作记录
     * 
     * @param ids 需要删除的检修操作记录ID
     * @return 结果
     */
    public int deleteCheckLogByIds(Long[] ids);

    /**
     * 删除检修操作记录信息
     * 
     * @param id 检修操作记录ID
     * @return 结果
     */
    public int deleteCheckLogById(Long id);
}
