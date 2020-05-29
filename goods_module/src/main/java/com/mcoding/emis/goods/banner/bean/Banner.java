package com.mcoding.emis.goods.banner.bean;

import java.io.Serializable;
import java.util.Date;

public class Banner  implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    public static final int IS_VALID_INTEGER_YES = 1;
    public static final int IS_VALID_INTEGER_NO = 0;
    
    private Integer id;

    private String title;

    private String img;

    private String link;

    private Integer orderno;

    private Date createtime;

    private Integer isvalid;

    private String brandcode;

    private String malltype;

    private Integer channelsId;

    private Integer ext1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public Integer getOrderno() {
        return orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getBrandcode() {
        return brandcode;
    }

    public void setBrandcode(String brandcode) {
        this.brandcode = brandcode == null ? null : brandcode.trim();
    }

    public String getMalltype() {
        return malltype;
    }

    public void setMalltype(String malltype) {
        this.malltype = malltype == null ? null : malltype.trim();
    }

    public Integer getChannelsId() {
		return channelsId;
	}

	public void setChannelsId(Integer channelsId) {
		this.channelsId = channelsId;
	}

	public Integer getExt1() {
        return ext1;
    }

    public void setExt1(Integer ext1) {
        this.ext1 = ext1;
    }
}