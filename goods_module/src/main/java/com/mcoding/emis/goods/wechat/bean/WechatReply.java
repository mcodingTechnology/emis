package com.mcoding.emis.goods.wechat.bean;

import java.io.Serializable;

import javax.persistence.Transient;
/**
 * ΢���Զ���ͼ��
 * @author moshow
 */
public class WechatReply implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer userid;

    private String keyword;

    private String title;

    private String image;

    private String content;
    
    private String newsMsgUrl;
    
    private String msgType;
    
    private String username;
    
    private Integer matchingType;
    
    private String brandCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public String getNewsMsgUrl() {
		return newsMsgUrl;
	}

	public void setNewsMsgUrl(String newsMsgUrl) {
		this.newsMsgUrl = newsMsgUrl;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Transient
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getMatchingType() {
		return matchingType;
	}

	public void setMatchingType(Integer matchingType) {
		this.matchingType = matchingType;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	
}