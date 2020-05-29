package com.mcoding.emis.goods.game.bean;

import java.io.Serializable;
import java.util.Date;

public class GameMemberResult implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 抽奖概率
	 */
	public static final String GAME_PRIZE_RANGE = "gameprizerange";
	/**
	 * 抽奖次数
	 */
	public static final String GAME_SHAKE_NUM = "gameshakenum";
	/**已经兑换奖品了**/
	public static final String GAME_RESULT_STATUS_RECEIVE = "yes";
	
    private Integer id;

    private String openid;

    private Integer rightnum;

    private String gaintitle;

    private Integer islottery;

    private Integer lotterynum;

    private Integer prizeid;

    private String prizename;

    private String brandcode;

    private Date createtime;

    private Date updatetime;

    private String gamename;

    private Integer gameid;
    
    private String ext;
    
    private String memberaddress;

    private String membername;

    private String memberphone;
    
    private String operator;

    private String ext1;
    
    private Integer subscribe;//非字段值，是否已关注
    private String nickname;//非字段值，微信昵称
    private Date gameendtime;//非字段值，活动结束时间
    private Integer prizetype;//非字段值，奖品类型
    private Integer gameMemberShareNum;//非字段值，用户分享次数
    private String prizeimg;//非字段值，奖品图片

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getRightnum() {
        return rightnum;
    }

    public void setRightnum(Integer rightnum) {
        this.rightnum = rightnum;
    }

    public String getGaintitle() {
        return gaintitle;
    }

    public void setGaintitle(String gaintitle) {
        this.gaintitle = gaintitle == null ? null : gaintitle.trim();
    }

    public Integer getIslottery() {
        return islottery;
    }

    public void setIslottery(Integer islottery) {
        this.islottery = islottery;
    }

    public Integer getLotterynum() {
        return lotterynum;
    }

    public void setLotterynum(Integer lotterynum) {
        this.lotterynum = lotterynum;
    }

    public Integer getPrizeid() {
        return prizeid;
    }

    public void setPrizeid(Integer prizeid) {
        this.prizeid = prizeid;
    }

    public String getPrizename() {
        return prizename;
    }

    public void setPrizename(String prizename) {
        this.prizename = prizename == null ? null : prizename.trim();
    }

    public String getBrandcode() {
        return brandcode;
    }

    public void setBrandcode(String brandcode) {
        this.brandcode = brandcode == null ? null : brandcode.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename == null ? null : gamename.trim();
    }

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public Integer getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	public String getMemberaddress() {
		return memberaddress;
	}

	public void setMemberaddress(String memberaddress) {
		this.memberaddress = memberaddress;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getMemberphone() {
		return memberphone;
	}

	public void setMemberphone(String memberphone) {
		this.memberphone = memberphone;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public Date getGameendtime() {
		return gameendtime;
	}

	public void setGameendtime(Date gameendtime) {
		this.gameendtime = gameendtime;
	}

	public Integer getPrizetype() {
		return prizetype;
	}

	public void setPrizetype(Integer prizetype) {
		this.prizetype = prizetype;
	}

	public Integer getGameMemberShareNum() {
		return gameMemberShareNum;
	}

	public void setGameMemberShareNum(Integer gameMemberShareNum) {
		this.gameMemberShareNum = gameMemberShareNum;
	}

	public String getPrizeimg() {
		return prizeimg;
	}

	public void setPrizeimg(String prizeimg) {
		this.prizeimg = prizeimg;
	}
}