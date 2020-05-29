package com.mcoding.emis.goods.icon.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Admin
 *
 */
public class Icon implements Serializable {
    private Integer id;

    private String iconCode;

    private String title;

    private String img;

    private String description;

    private String mallType;

    private Date createTime;

    private Date updateTime;
    
    private String isValid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIconCode() {
        return iconCode;
    }

    public void setIconCode(String iconCode) {
        this.iconCode = iconCode == null ? null : iconCode.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getMallType() {
        return mallType;
    }

    public void setMallType(String mallType) {
        this.mallType = mallType == null ? null : mallType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

}