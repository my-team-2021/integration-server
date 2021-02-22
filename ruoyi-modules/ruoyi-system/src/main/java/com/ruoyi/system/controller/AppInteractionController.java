package com.ruoyi.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.system.api.RemoteFileService;
import com.ruoyi.system.api.domain.SysFile;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.domain.CheckItemDetail;
import com.ruoyi.system.domain.CheckLog;
import com.ruoyi.system.domain.DeviceInfo;
import com.ruoyi.system.service.ICheckItemDetailService;
import com.ruoyi.system.service.ICheckLogService;
import com.ruoyi.system.service.IDeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
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
    private ICheckLogService checkLogService;
    @Autowired
    private ICheckItemDetailService checkItemDetailService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RemoteFileService remoteFileService;

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
    public AjaxResult upload(@RequestParam("uploadFiles") MultipartFile[] files) {
        if (files.length > 0) {
            for (MultipartFile file : files) {
                R<SysFile> fileResult = remoteFileService.upload(file);
                if (StringUtils.isNull(fileResult) || StringUtils.isNull(fileResult.getData())) {
                    return AjaxResult.error("文件服务异常，请联系管理员");
                }
            }
        }
        return AjaxResult.success();
    }

    /**
     * 检修表单提交
     */
    @PreAuthorize(hasPermi = "system:appInteraction:commitCheckInfo")
    @GetMapping("/commitCheckInfo")
    public AjaxResult commitCheckInfo(@RequestBody JSONObject request) {
        LoginUser loginUser = tokenService.getLoginUser();
        String username = loginUser.getUsername();
        String userid = loginUser.getUserid().toString();

        //1、设备检查主信息保存
        String factoryId = request.getString("factoryId");
        String roomId = request.getString("roomId");
        String inputCode = request.getString("inputCode");
        String type = request.getString("type");
        String version = request.getString("version");
        CheckLog checkLog = new CheckLog();
        checkLog.setFactoryId(factoryId);
        checkLog.setRoomId(roomId);
        checkLog.setInputCode(inputCode);
        checkLog.setType((long) ("fire".equals(type) ? 1 : 0));
        checkLog.setVersion(version);
        checkLog.setCreateByName(username);
        checkLog.setCreateBy(userid);
        Date now = new Date();
        checkLog.setCreateTime(now);
        checkLogService.insertCheckLog(checkLog);

        //2、设备子检查项信息保存
        List<CheckItemDetail> checkItemDetailList = new ArrayList<>();
        JSONArray jsonArray = request.getJSONArray("check");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            CheckItemDetail itemDetail = new CheckItemDetail();
            String selecteds = jsonObject.getString("selecteds");
            String fontRemark = jsonObject.getString("fontRemark");
            String imgRemarks = jsonObject.getString("imgRemarks");
            String videoRemarks = jsonObject.getString("videoRemarks");
            itemDetail.setParentId(checkLog.getId());
            itemDetail.setCreateByName(loginUser.getUsername());
            itemDetail.setCreateBy(userid);
            itemDetail.setCreateTime(now);
            itemDetail.setSelecteds(selecteds);
            itemDetail.setFontRemark(fontRemark);
            itemDetail.setImgRemarks(imgRemarks);
            itemDetail.setVideoRemark(videoRemarks);
            itemDetail.setVersion(version);
            checkItemDetailList.add(itemDetail);
        }

        for (CheckItemDetail itemDetail : checkItemDetailList){
            checkItemDetailService.insertCheckItemDetail(itemDetail);
        }
        return AjaxResult.success();
    }
}
