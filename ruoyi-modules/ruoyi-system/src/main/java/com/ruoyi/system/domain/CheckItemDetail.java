package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 检修项详情对象 check_item_detail
 * 
 * @author zengjl
 * @date 2021-02-20
 */
public class CheckItemDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 关联id */
    @Excel(name = "关联id")
    private Long parentId;

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

    /** 检修版本 */
    @Excel(name = "检修版本")
    private String version;

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
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
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
    public void setVersion(String version) 
    {
        this.version = version;
    }

    public String getVersion() 
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
            .append("parentId", getParentId())
            .append("selecteds", getSelecteds())
            .append("fontRemark", getFontRemark())
            .append("imgRemarks", getImgRemarks())
            .append("videoRemark", getVideoRemark())
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
