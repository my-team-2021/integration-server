package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.core.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.CheckItemDetail;
import com.ruoyi.system.mapper.CheckLogMapper;
import com.ruoyi.system.domain.CheckLog;
import com.ruoyi.system.service.ICheckLogService;

/**
 * 检修操作记录Service业务层处理
 * 
 * @author zengjl
 * @date 2021-02-20
 */
@Service
public class CheckLogServiceImpl implements ICheckLogService 
{
    @Autowired
    private CheckLogMapper checkLogMapper;

    /**
     * 查询检修操作记录
     * 
     * @param id 检修操作记录ID
     * @return 检修操作记录
     */
    @Override
    public CheckLog selectCheckLogById(Long id)
    {
        return checkLogMapper.selectCheckLogById(id);
    }

    /**
     * 查询检修操作记录列表
     * 
     * @param checkLog 检修操作记录
     * @return 检修操作记录
     */
    @Override
    public List<CheckLog> selectCheckLogList(CheckLog checkLog)
    {
        return checkLogMapper.selectCheckLogList(checkLog);
    }

    /**
     * 新增检修操作记录
     * 
     * @param checkLog 检修操作记录
     * @return 结果
     */
    @Transactional
    @Override
    public int insertCheckLog(CheckLog checkLog)
    {
        checkLog.setCreateTime(DateUtils.getNowDate());
        int rows = checkLogMapper.insertCheckLog(checkLog);
        insertCheckItemDetail(checkLog);
        return rows;
    }

    /**
     * 修改检修操作记录
     * 
     * @param checkLog 检修操作记录
     * @return 结果
     */
    @Transactional
    @Override
    public int updateCheckLog(CheckLog checkLog)
    {
        checkLog.setUpdateTime(DateUtils.getNowDate());
        checkLogMapper.deleteCheckItemDetailByParentId(checkLog.getId());
        insertCheckItemDetail(checkLog);
        return checkLogMapper.updateCheckLog(checkLog);
    }

    /**
     * 批量删除检修操作记录
     * 
     * @param ids 需要删除的检修操作记录ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCheckLogByIds(Long[] ids)
    {
        checkLogMapper.deleteCheckItemDetailByParentIds(ids);
        return checkLogMapper.deleteCheckLogByIds(ids);
    }

    /**
     * 删除检修操作记录信息
     * 
     * @param id 检修操作记录ID
     * @return 结果
     */
    @Override
    public int deleteCheckLogById(Long id)
    {
        checkLogMapper.deleteCheckItemDetailByParentId(id);
        return checkLogMapper.deleteCheckLogById(id);
    }

    /**
     * 新增检修项详情信息
     * 
     * @param checkLog 检修操作记录对象
     */
    public void insertCheckItemDetail(CheckLog checkLog)
    {
        List<CheckItemDetail> checkItemDetailList = checkLog.getCheckItemDetailList();
        Long id = checkLog.getId();
        if (StringUtils.isNotNull(checkItemDetailList))
        {
            List<CheckItemDetail> list = new ArrayList<CheckItemDetail>();
            for (CheckItemDetail checkItemDetail : checkItemDetailList)
            {
                checkItemDetail.setParentId(id);
                list.add(checkItemDetail);
            }
            if (list.size() > 0)
            {
                checkLogMapper.batchCheckItemDetail(list);
            }
        }
    }
}
