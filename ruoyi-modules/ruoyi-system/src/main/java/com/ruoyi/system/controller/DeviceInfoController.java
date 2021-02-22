package com.ruoyi.system.controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.json.JSONUtil;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.domain.DeviceInfo;
import com.ruoyi.system.service.IDeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 设备信息维护Controller
 * 
 * @author zengjl
 * @date 2021-02-19
 */
@RestController
@RequestMapping("/device")
public class DeviceInfoController extends BaseController
{
    @Autowired
    private IDeviceInfoService deviceInfoService;

    /**
     * 查询设备信息维护列表
     */
    @PreAuthorize(hasPermi = "system:device:list")
    @GetMapping("/list")
    public TableDataInfo list(DeviceInfo deviceInfo)
    {
        deviceInfo.setStatus(1);
        startPage();
        List<DeviceInfo> list = deviceInfoService.selectDeviceInfoList(deviceInfo);
        return getDataTable(list);
    }

    /**
     * 导出设备信息维护列表
     */
    @PreAuthorize(hasPermi = "system:device:export")
    @Log(title = "设备信息维护", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceInfo deviceInfo) throws IOException
    {
        List<DeviceInfo> list = deviceInfoService.selectDeviceInfoList(deviceInfo);
        ExcelUtil<DeviceInfo> util = new ExcelUtil<DeviceInfo>(DeviceInfo.class);
        util.exportExcel(response, list, "device");
    }

    /**
     * 获取设备信息维护详细信息
     */
    @PreAuthorize(hasPermi = "system:device:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(deviceInfoService.selectDeviceInfoById(id));
    }

    /**
     * 新增设备信息维护
     */
    @PreAuthorize(hasPermi = "system:device:add")
    @Log(title = "设备信息维护", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceInfo deviceInfo)
    {
        return toAjax(deviceInfoService.insertDeviceInfo(deviceInfo));
    }

    /**
     * 修改设备信息维护
     */
    @PreAuthorize(hasPermi = "system:device:edit")
    @Log(title = "设备信息维护", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceInfo deviceInfo)
    {
        return toAjax(deviceInfoService.updateDeviceInfo(deviceInfo));
    }

    /**
     * 删除设备信息维护
     */
    @PreAuthorize(hasPermi = "system:device:remove")
    @Log(title = "设备信息维护", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(deviceInfoService.deleteDeviceInfoByIds(ids));
    }

    /**
     * 获取设备信息详细信息
     */
//    @PreAuthorize(hasPermi = "system:equpiment:query")
    @GetMapping(value = "/qrCodeExport/{id}")
    public AjaxResult qrCodeExport(@PathVariable("id") Long id)
    {
        DeviceInfo deviceInfo = deviceInfoService.selectDeviceInfoById(id);
        String json = JSONUtil.toJsonStr(deviceInfo);
        //base64
        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        String generateAsBase64 = QrCodeUtil.generateAsBase64(json, config, "png");
        deviceInfo.setPicture(generateAsBase64);
        return AjaxResult.success(deviceInfo);
    }
}
