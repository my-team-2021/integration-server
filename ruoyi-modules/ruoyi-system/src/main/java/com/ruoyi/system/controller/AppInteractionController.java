package com.ruoyi.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
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
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

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
    public R deviceList() {
        LoginUser loginUser = tokenService.getLoginUser();
        startPage();
        List<DeviceInfo> list = deviceInfoService.selectCheckEquipsOfUser(loginUser.getUserid());
        PageInfo page = new PageInfo(list);
        JSONObject pageInfo = new JSONObject();
        pageInfo.put("total", page.getTotal());
        pageInfo.put("pageNum", page.getPageNum());
        pageInfo.put("pages", page.getPages());
        pageInfo.put("list", page.getList());

        return R.ok(pageInfo);
    }

    /**
     * 检修视频、图片上传
     */
    @PreAuthorize(hasPermi = "system:appInteraction:upload")
    @PostMapping("/upload")
    public AjaxResult upload(@RequestParam("uploadFiles") MultipartFile[] files) {
        if (files.length > 0) {
            Map<String, String> resultMap = new HashMap<>();
            for (MultipartFile file : files) {
                String originName = file.getOriginalFilename();
                R<SysFile> fileResult = remoteFileService.upload(file);
                if (StringUtils.isNull(fileResult) || StringUtils.isNull(fileResult.getData())) {
                    return AjaxResult.error("文件服务异常，请联系管理员");
                }
                // 返回的是文件的绝对路径
                resultMap.put(originName, fileResult.getData().getUrl());
            }
            return AjaxResult.success(resultMap);
        } else {
            return AjaxResult.error("文件上传参数异常");
        }
    }

    /**
     * 检修表单提交
     */
    @PreAuthorize(hasPermi = "system:appInteraction:commitCheckInfo")
    @PostMapping("/commitCheckInfo")
    public AjaxResult commitCheckInfo(@RequestBody JSONObject request) {
        logger.info("表单提交请求：{}", request.toJSONString());
        LoginUser loginUser = tokenService.getLoginUser();
        logger.info("提交表单的当前用户信息：{}", JSONObject.toJSONString(loginUser));
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
        checkLog.setUpdateByName(username);
        checkLog.setUpdateBy(userid);
        checkLog.setCheckUser(username);
        Date now = new Date();
        checkLog.setCreateTime(now);
        checkLog.setCheckTime(now);
        checkLog.setUpdateTime(now);
        checkLogService.insertCheckLog(checkLog);

        //2、设备子检查项信息保存
        List<CheckItemDetail> checkItemDetailList = new ArrayList<>();

        JSONArray jsonArray = JSONObject.parseObject(JSONObject.toJSONString(request)).getJSONArray("check");
        logger.info("check集合为:{}", jsonArray.toJSONString());
        if (CollectionUtils.isEmpty(jsonArray)) {
            return AjaxResult.error("check表单为空");
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            CheckItemDetail itemDetail = new CheckItemDetail();
            String selecteds = jsonObject.getString("selecteds");
            String fontRemark = jsonObject.getString("fontRemark");
            String imgRemarks = jsonObject.getString("imgRemarks");
            String videoRemarks = jsonObject.getString("videoRemarks");
            itemDetail.setParentId(checkLog.getId());
            itemDetail.setSelecteds(selecteds);
            itemDetail.setFontRemark(fontRemark);
            itemDetail.setImgRemarks(imgRemarks);
            itemDetail.setVideoRemark(videoRemarks);
            itemDetail.setCreateByName(loginUser.getUsername());
            itemDetail.setCreateBy(userid);
            itemDetail.setCreateTime(now);
            itemDetail.setUpdateByName(username);
            itemDetail.setUpdateBy(userid);
            itemDetail.setUpdateTime(now);
            itemDetail.setVersion(version);
            checkItemDetailList.add(itemDetail);
        }

        for (CheckItemDetail itemDetail : checkItemDetailList) {
            checkItemDetailService.insertCheckItemDetail(itemDetail);
        }
        return AjaxResult.success();
    }
}
