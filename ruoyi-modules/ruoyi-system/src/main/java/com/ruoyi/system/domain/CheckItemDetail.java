package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 检修项详情对象 check_item_detail
 * 
 * @author zengjl
 * @date 2021-02-19
 */
public class CheckItemDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 厂房名称 */
    @Excel(name = "厂房名称")
    private String factoryId;

    /** 房间编号 */
    @Excel(name = "房间编号")
    private String roomId;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String inputCode;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private Long type;

    /** 选中的选中集合，以逗号隔开 */
    @Excel(name = "选中的选中集合，以逗号隔开")
    private String selecteds;

    /** 文字备注 */
    @Excel(name = "文字备注")
    private String fontRemark;

    /** 图片备注（图片路径，逗号隔开） */
    @Excel(name = "图片备注", readConverterExp = "图=片路径，逗号隔开")
    private String imgRemarks;

    /** 视频备注，一个检查项只能一个视频 */
    @Excel(name = "视频备注，一个检查项只能一个视频")
    private String videoRemark;

    /** 检查人姓名 */
    @Excel(name = "检查人姓名")
    private String checkUser;

    /** 检查时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkTime;

    /** 检修版本 */
    @Excel(name = "检修版本")
    private Long version;

    /** 创建人名称 */
    @Excel(name = "创建人名称")
    private String createByName;

    /** 最后修改人名称 */
    @Excel(name = "最后修改人名称")
    private String updateByName;

    /** 逻辑删除标记 */
    @Excel(name = "逻辑删除标记")
    private Integer enabled;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFactoryId(String factoryId) 
    {
        this.factoryId = factoryId;
    }

    public String getFactoryId() 
    {
        return factoryId;
    }
    public void setRoomId(String roomId) 
    {
        this.roomId = roomId;
    }

    public String getRoomId() 
    {
        return roomId;
    }
    public void setInputCode(String inputCode) 
    {
        this.inputCode = inputCode;
    }

    public String getInputCode() 
    {
        return inputCode;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setSelecteds(String selecteds) 
    {
        this.selecteds = selecteds;
    }

    public String getSelecteds() 
    {
        return selecteds;
    }
    public void setFontRemark(String fontRemark) 
    {
        this.fontRemark = fontRemark;
    }

    public String getFontRemark() 
    {
        return fontRemark;
    }
    public void setImgRemarks(String imgRemarks) 
    {
        this.imgRemarks = imgRemarks;
    }

    public String getImgRemarks() 
    {
        return imgRemarks;
    }
    public void setVideoRemark(String videoRemark) 
    {
        this.videoRemark = videoRemark;
    }

    public String getVideoRemark() 
    {
        return videoRemark;
    }
    public void setCheckUser(String checkUser) 
    {
        this.checkUser = checkUser;
    }

    public String getCheckUser() 
    {
        return checkUser;
    }
    public void setCheckTime(Date checkTime) 
    {
        this.checkTime = checkTime;
    }

    public Date getCheckTime() 
    {
        return checkTime;
    }
    public void setVersion(Long version)
    {
        this.version = version;
    }

    public Long getVersion()
    {
        return version;
    }
    public void setCreateByName(String createByName) 
    {
        this.createByName = createByName;
    }

    public String getCreateByName() 
    {
        return createByName;
    }
    public void setUpdateByName(String updateByName) 
    {
        this.updateByName = updateByName;
    }

    public String getUpdateByName() 
    {
        return updateByName;
    }
    public void setEnabled(Integer enabled) 
    {
        this.enabled = enabled;
    }

    public Integer getEnabled() 
    {
        return enabled;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("factoryId", getFactoryId())
            .append("roomId", getRoomId())
            .append("inputCode", getInputCode())
            .append("type", getType())
            .append("selecteds", getSelecteds())
            .append("fontRemark", getFontRemark())
            .append("imgRemarks", getImgRemarks())
            .append("videoRemark", getVideoRemark())
            .append("checkUser", getCheckUser())
            .append("checkTime", getCheckTime())
            .append("version", getVersion())
            .append("createBy", getCreateBy())
            .append("createByName", getCreateByName())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateByName", getUpdateByName())
            .append("updateTime", getUpdateTime())
            .append("enabled", getEnabled())
            .toString();
    }
}
