package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CheckItemDetailMapper;
import com.ruoyi.system.domain.CheckItemDetail;
import com.ruoyi.system.service.ICheckItemDetailService;

/**
 * 检修项详情Service业务层处理
 * 
 * @author zengjl
 * @date 2021-02-22
 */
@Service
public class CheckItemDetailServiceImpl implements ICheckItemDetailService 
{
    @Autowired
    private CheckItemDetailMapper checkItemDetailMapper;

    /**
     * 查询检修项详情
     * 
     * @param id 检修项详情ID
     * @return 检修项详情
     */
    @Override
    public CheckItemDetail selectCheckItemDetailById(Long id)
    {
        return checkItemDetailMapper.selectCheckItemDetailById(id);
    }

    /**
     * 查询检修项详情列表
     * 
     * @param checkItemDetail 检修项详情
     * @return 检修项详情
     */
    @Override
    public List<CheckItemDetail> selectCheckItemDetailList(CheckItemDetail checkItemDetail)
    {
        return checkItemDetailMapper.selectCheckItemDetailList(checkItemDetail);
    }

    /**
     * 新增检修项详情
     * 
     * @param checkItemDetail 检修项详情
     * @return 结果
     */
    @Override
    public int insertCheckItemDetail(CheckItemDetail checkItemDetail)
    {
        checkItemDetail.setCreateTime(DateUtils.getNowDate());
        return checkItemDetailMapper.insertCheckItemDetail(checkItemDetail);
    }

    /**
     * 修改检修项详情
     * 
     * @param checkItemDetail 检修项详情
     * @return 结果
     */
    @Override
    public int updateCheckItemDetail(CheckItemDetail checkItemDetail)
    {
        checkItemDetail.setUpdateTime(DateUtils.getNowDate());
        return checkItemDetailMapper.updateCheckItemDetail(checkItemDetail);
    }

    /**
     * 批量删除检修项详情
     * 
     * @param ids 需要删除的检修项详情ID
     * @return 结果
     */
    @Override
    public int deleteCheckItemDetailByIds(Long[] ids)
    {
        return checkItemDetailMapper.deleteCheckItemDetailByIds(ids);
    }

    /**
     * 删除检修项详情信息
     * 
     * @param id 检修项详情ID
     * @return 结果
     */
    @Override
    public int deleteCheckItemDetailById(Long id)
    {
        return checkItemDetailMapper.deleteCheckItemDetailById(id);
    }
}
