package com.mcoding.emis.goods.mallGame.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.mcoding.emis.member.bean.member.Member;

public class MallgameResult implements Serializable{
	private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer gameid;

    private String mobilephone;

    private String openid;
    
    private String nickname;

    private Integer islottery;

    private Integer isorder;

    private Integer productid;

    private String productname;

    private String productcoverimg;

    private Integer giftid;

    private Integer gainpoint;

    private Integer nums;

    private String brandcode;

    private String ext;

    private String ext1;

    private Date drawdate;

    private Date createtime;
    
    private Member member;
    private Integer drawNum;
    private List<MallgameGift> giftList;

    /**
     * 非字段，游戏名称
     */
    private String gamename;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getIslottery() {
        return islottery;
    }

    public void setIslottery(Integer islottery) {
        this.islottery = islottery;
    }

    public Integer getIsorder() {
        return isorder;
    }

    public void setIsorder(Integer isorder) {
        this.isorder = isorder;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public String getProductcoverimg() {
        return productcoverimg;
    }

    public void setProductcoverimg(String productcoverimg) {
        this.productcoverimg = productcoverimg == null ? null : productcoverimg.trim();
    }

    public Integer getGiftid() {
        return giftid;
    }

    public void setGiftid(Integer giftid) {
        this.giftid = giftid;
    }

    public Integer getGainpoint() {
        return gainpoint;
    }

    public void setGainpoint(Integer gainpoint) {
        this.gainpoint = gainpoint;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public String getBrandcode() {
        return brandcode;
    }

    public void setBrandcode(String brandcode) {
        this.brandcode = brandcode == null ? null : brandcode.trim();
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public Date getDrawdate() {
        return drawdate;
    }

    public void setDrawdate(Date drawdate) {
        this.drawdate = drawdate;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Integer getDrawNum() {
		return drawNum;
	}

	public void setDrawNum(Integer drawNum) {
		this.drawNum = drawNum;
	}

	public List<MallgameGift> getGiftList() {
		return giftList;
	}

	public void setGiftList(List<MallgameGift> giftList) {
		this.giftList = giftList;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGamename() {
		return gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

}