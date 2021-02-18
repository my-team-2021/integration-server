package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 设备信息对象 equipment_info
 * 
 * @author zengjl
 * @date 2021-02-18
 */
public class EquipmentInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String name;

    /** 设备详情(参数信息) */
    @Excel(name = "设备详情(参数信息)")
    private String description;

    /** 设备图片 */
    @Excel(name = "设备图片")
    private String picture;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String inputCode;

    /** 质保年份 */
    @Excel(name = "质保年份")
    private Long keepYears;

    /** 设备状态 0 停用 1 正常 2报废  */
    @Excel(name = "设备状态 0 停用 1 正常 2报废 ")
    private Integer status;

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
    public void setInputCode(String inputCode) 
    {
        this.inputCode = inputCode;
    }

    public String getInputCode() 
    {
        return inputCode;
    }
    public void setKeepYears(Long keepYears) 
    {
        this.keepYears = keepYears;
    }

    public Long getKeepYears() 
    {
        return keepYears;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
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
            .append("name", getName())
            .append("description", getDescription())
            .append("picture", getPicture())
            .append("inputCode", getInputCode())
            .append("keepYears", getKeepYears())
            .append("status", getStatus())
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
