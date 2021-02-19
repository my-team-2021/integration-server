package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.system.domain.EquipmentInfo;
import com.ruoyi.system.service.IEquipmentInfoService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 设备信息Controller
 * 
 * @author zengjl
 * @date 2021-02-18
 */
@RestController
@RequestMapping("/equpiment")
public class EquipmentInfoController extends BaseController
{
    @Autowired
    private IEquipmentInfoService equipmentInfoService;

    /**
     * 查询设备信息列表
     */
    @PreAuthorize(hasPermi = "system:equpiment:list")
    @GetMapping("/list")
    public TableDataInfo list(EquipmentInfo equipmentInfo)
    {
        startPage();
        List<EquipmentInfo> list = equipmentInfoService.selectEquipmentInfoList(equipmentInfo);
        return getDataTable(list);
    }

    /**
     * 导出设备信息列表
     */
    @PreAuthorize(hasPermi = "system:equpiment:export")
    @Log(title = "设备信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EquipmentInfo equipmentInfo) throws IOException
    {
        List<EquipmentInfo> list = equipmentInfoService.selectEquipmentInfoList(equipmentInfo);
        ExcelUtil<EquipmentInfo> util = new ExcelUtil<EquipmentInfo>(EquipmentInfo.class);
        util.exportExcel(response, list, "equpiment");
    }

    /**
     * 获取设备信息详细信息
     */
    @PreAuthorize(hasPermi = "system:equpiment:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(equipmentInfoService.selectEquipmentInfoById(id));
    }

    /**
     * 新增设备信息
     */
    @PreAuthorize(hasPermi = "system:equpiment:add")
    @Log(title = "设备信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EquipmentInfo equipmentInfo)
    {
        return toAjax(equipmentInfoService.insertEquipmentInfo(equipmentInfo));
    }

    /**
     * 修改设备信息
     */
    @PreAuthorize(hasPermi = "system:equpiment:edit")
    @Log(title = "设备信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EquipmentInfo equipmentInfo)
    {
        return toAjax(equipmentInfoService.updateEquipmentInfo(equipmentInfo));
    }

    /**
     * 删除设备信息
     */
    @PreAuthorize(hasPermi = "system:equpiment:remove")
    @Log(title = "设备信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(equipmentInfoService.deleteEquipmentInfoByIds(ids));
    }

    /**
     * 导出设备二维码信息
     */
//    @PreAuthorize(hasPermi = "system:equpiment:qrCodeExport")
//    @Log(title = "导出设备二维码信息", businessType = BusinessType.EXPORT)
//    @PostMapping("/qrCodeExport")
//    public void qrCodeExport(HttpServletResponse response, EquipmentInfo equipmentInfo) throws IOException
//    {
//        String json = JSONUtil.toJsonStr(equipmentInfo);
////        List<EquipmentInfo> list = equipmentInfoService.selectEquipmentInfoList(equipmentInfo);
////        ExcelUtil<EquipmentInfo> util = new ExcelUtil<EquipmentInfo>(EquipmentInfo.class);
////        util.exportExcel(response, list, "equpiment");
//
//        // 生成指定url对应的二维码到文件，宽和高都是300像素
//        QrCodeUtil.generate(json, 300, 300, FileUtil.file("d:/qrcode.jpg"));
//
//        // 识别二维码
//        // decode -> "http://hutool.cn/"
//        String decode = QrCodeUtil.decode(FileUtil.file("d:/qrcode.jpg"));
//        System.out.println("decode:" + decode);
//
//        //base64
//        QrConfig config = new QrConfig(300, 300);
//        // 设置边距，既二维码和背景之间的边距
//        config.setMargin(3);
//        String generateAsBase64 = QrCodeUtil.generateAsBase64(json, config, "png");
//        System.out.println("generateAsBase64:" + generateAsBase64);
//    }

    /**
     * 获取设备信息详细信息
     */
//    @PreAuthorize(hasPermi = "system:equpiment:query")
    @GetMapping(value = "/qrCodeExport/{id}")
    public AjaxResult qrCodeExport(@PathVariable("id") Long id)
    {
        EquipmentInfo equipmentInfo = equipmentInfoService.selectEquipmentInfoById(id);
        String json = JSONUtil.toJsonStr(equipmentInfo);

        // 生成指定url对应的二维码到文件，宽和高都是300像素
        QrCodeUtil.generate(json, 300, 300, FileUtil.file("d:/qrcode.jpg"));

        // 识别二维码
        // decode -> "http://hutool.cn/"
        String decode = QrCodeUtil.decode(FileUtil.file("d:/qrcode.jpg"));
        System.out.println("decode:" + decode);

        //base64
        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        String generateAsBase64 = QrCodeUtil.generateAsBase64(json, config, "png");
        System.out.println("generateAsBase64:" + generateAsBase64);
        equipmentInfo.setPicture(generateAsBase64);
        return AjaxResult.success(equipmentInfo);
    }
}
