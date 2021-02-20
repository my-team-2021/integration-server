package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.system.domain.CheckLog;
import com.ruoyi.system.service.ICheckLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 检修操作记录Controller
 * 
 * @author zengjl
 * @date 2021-02-19
 */
@RestController
@RequestMapping("/log")
public class CheckLogController extends BaseController
{
    @Autowired
    private ICheckLogService checkLogService;

    /**
     * 查询检修操作记录列表
     */
    @PreAuthorize(hasPermi = "system:log:list")
    @GetMapping("/list")
    public TableDataInfo list(CheckLog checkLog)
    {
        startPage();
        List<CheckLog> list = checkLogService.selectCheckLogList(checkLog);
        return getDataTable(list);
    }

    /**
     * 导出检修操作记录列表
     */
    @PreAuthorize(hasPermi = "system:log:export")
    @Log(title = "检修操作记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CheckLog checkLog) throws IOException
    {
        List<CheckLog> list = checkLogService.selectCheckLogList(checkLog);
        ExcelUtil<CheckLog> util = new ExcelUtil<CheckLog>(CheckLog.class);
        util.exportExcel(response, list, "log");
    }

    /**
     * 获取检修操作记录详细信息
     */
    @PreAuthorize(hasPermi = "system:log:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(checkLogService.selectCheckLogById(id));
    }

    /**
     * 新增检修操作记录
     */
    @PreAuthorize(hasPermi = "system:log:add")
    @Log(title = "检修操作记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckLog checkLog)
    {
        return toAjax(checkLogService.insertCheckLog(checkLog));
    }

    /**
     * 修改检修操作记录
     */
    @PreAuthorize(hasPermi = "system:log:edit")
    @Log(title = "检修操作记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckLog checkLog)
    {
        return toAjax(checkLogService.updateCheckLog(checkLog));
    }

    /**
     * 删除检修操作记录
     */
    @PreAuthorize(hasPermi = "system:log:remove")
    @Log(title = "检修操作记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(checkLogService.deleteCheckLogByIds(ids));
    }
}
