package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CheckItemDetail;

/**
 * 检修项详情Service接口
 * 
 * @author zengjl
 * @date 2021-02-22
 */
public interface ICheckItemDetailService 
{
    /**
     * 查询检修项详情
     * 
     * @param id 检修项详情ID
     * @return 检修项详情
     */
    public CheckItemDetail selectCheckItemDetailById(Long id);

    /**
     * 查询检修项详情列表
     * 
     * @param checkItemDetail 检修项详情
     * @return 检修项详情集合
     */
    public List<CheckItemDetail> selectCheckItemDetailList(CheckItemDetail checkItemDetail);

    /**
     * 新增检修项详情
     * 
     * @param checkItemDetail 检修项详情
     * @return 结果
     */
    public int insertCheckItemDetail(CheckItemDetail checkItemDetail);

    /**
     * 修改检修项详情
     * 
     * @param checkItemDetail 检修项详情
     * @return 结果
     */
    public int updateCheckItemDetail(CheckItemDetail checkItemDetail);

    /**
     * 批量删除检修项详情
     * 
     * @param ids 需要删除的检修项详情ID
     * @return 结果
     */
    public int deleteCheckItemDetailByIds(Long[] ids);

    /**
     * 删除检修项详情信息
     * 
     * @param id 检修项详情ID
     * @return 结果
     */
    public int deleteCheckItemDetailById(Long id);
}
