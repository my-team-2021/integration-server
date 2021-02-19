package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 设备信息维护对象 device_info
 * 
 * @author zengjl
 * @date 2021-02-19
 */
public class DeviceInfo extends BaseEntity
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

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String name;

    /** 设备详情(参数信息) */
    @Excel(name = "设备详情(参数信息)")
    private String description;

    /** 设备图片 */
    @Excel(name = "设备图片")
    private String picture;

    /** 设备状态 0 停用 1 正常 2报废  */
    @Excel(name = "设备状态 0 停用 1 正常 2报废 ")
    private Integer status;

    /** 巡检周期 */
    @Excel(name = "巡检周期")
    private Long period;

    /** 最近检修时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近检修时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastCheckTime;

    /** 下次检修时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下次检修时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nextCheckTime;

    /** 创建人名称 */
    @Excel(name = "创建人名称")
    private String createByName;

    /** 最后修改人名称 */
    @Excel(name = "最后修改人名称")
    private String updateByName;

    /** 逻辑删除标记 */
    @Excel(name = "逻辑删除标记")
    private Integer enabled;

    /** 版本字段 */
    @Excel(name = "版本字段")
    private Integer version;

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    public String getPicture() 
    {
        return picture;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setPeriod(Long period) 
    {
        this.period = period;
    }

    public Long getPeriod() 
    {
        return period;
    }
    public void setLastCheckTime(Date lastCheckTime) 
    {
        this.lastCheckTime = lastCheckTime;
    }

    public Date getLastCheckTime() 
    {
        return lastCheckTime;
    }
    public void setNextCheckTime(Date nextCheckTime) 
    {
        this.nextCheckTime = nextCheckTime;
    }

    public Date getNextCheckTime() 
    {
        return nextCheckTime;
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
    public void setVersion(Integer version) 
    {
        this.version = version;
    }

    public Integer getVersion() 
    {
        return version;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("factoryId", getFactoryId())
            .append("roomId", getRoomId())
            .append("inputCode", getInputCode())
            .append("name", getName())
            .append("description", getDescription())
            .append("picture", getPicture())
            .append("status", getStatus())
            .append("period", getPeriod())
            .append("lastCheckTime", getLastCheckTime())
            .append("nextCheckTime", getNextCheckTime())
            .append("createBy", getCreateBy())
            .append("createByName", getCreateByName())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateByName", getUpdateByName())
            .append("updateTime", getUpdateTime())
            .append("enabled", getEnabled())
            .append("version", getVersion())
            .toString();
    }
}
