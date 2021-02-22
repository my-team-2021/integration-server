package com.ruoyi.system.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.domain.DeviceInfo;
import com.ruoyi.system.service.IDeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * App交互Controller
 *
 * @author bruceliu
 * @date 2021-02-22
 */
@RestController
@RequestMapping("/appInteraction")
public class AppInteractionController extends BaseController {
    @Autowired
    private IDeviceInfoService deviceInfoService;
    @Autowired
    private TokenService tokenService;

    /**
     * 预警信息列表
     */
    @PreAuthorize(hasPermi = "system:appInteraction:deviceList")
    @GetMapping("/deviceList")
    public TableDataInfo deviceList() {
        LoginUser loginUser = tokenService.getLoginUser();
        startPage();
        List<DeviceInfo> list = deviceInfoService.selectCheckEquipsOfUser(loginUser.getUserid());
        return getDataTable(list);
    }

    /**
     * 检修视频、图片上传
     */
    @PreAuthorize(hasPermi = "system:appInteraction:upload")
    @GetMapping("/upload")
    public TableDataInfo upload(){
        return null;
    }

    /**
     * 检修表单提交
     */
    @PreAuthorize(hasPermi = "system:appInteraction:deviceAdd")
    @GetMapping("/deviceAdd")
    public TableDataInfo deviceAdd(){
        return null;
    }


}
