package com.mcoding.emis.goods.card.bean;

import java.io.Serializable;
import java.util.Date;

public class Card implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**使用状态 已使用**/
	public static final String CARD_STATUS_USED = "USED";
//	/**使用状态 未使用**/
//	public static final String CARD_STATUS_UN_USED = "UN_USED";
	/**卡券未兑换**/
	public static final String CARD_STATUS_UN_EXCAHGED = "UN_EXCHAGED";
	/**卡券已兑换,未使用**/
	public static final String CARD_STATUS_EXCAHGED = "EXCHAGED";
	/**单张特权卡券码未兑换**/
	public static final String CARD_STATUS_SINGLE_UN_EXCAHGED = "SINGLE_UN_EXCHAGED";
	/**单张特权卡券码已兑换**/
	public static final String CARD_STATUS_SINGLE_EXCAHGED = "SINGLE_EXCHAGED";
	/**多张张特权卡券码未兑换**/
	public static final String CARD_STATUS_MANY_UN_EXCAHGED = "MANY_UN_EXCHAGED";
	/**多张特权卡券码已兑换**/
	public static final String CARD_STATUS_MANY_EXCAHGED = "MANY_EXCHAGED";
	
    private Integer id;

    private Integer cardtypeid;

    private String cardtypename;

    private String cardcode;

    private Date createtime;

    private Date validtime;

    private Date usetime;

    private String openid;

    private Integer memberid;

    private Integer isvalid;

    private String ext1;

    private String ext2;

    private String ext3;

    private String ext4;

    private String ext5;
    
    private CardType cardType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardtypeid() {
        return cardtypeid;
    }

    public void setCardtypeid(Integer cardtypeid) {
        this.cardtypeid = cardtypeid;
    }

    public String getCardtypename() {
        return cardtypename;
    }

    public void setCardtypename(String cardtypename) {
        this.cardtypename = cardtypename == null ? null : cardtypename.trim();
    }

    public String getCardcode() {
        return cardcode;
    }

    public void setCardcode(String cardcode) {
        this.cardcode = cardcode == null ? null : cardcode.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getValidtime() {
        return validtime;
    }

    public void setValidtime(Date validtime) {
        this.validtime = validtime;
    }

    public Date getUsetime() {
        return usetime;
    }

    public void setUsetime(Date usetime) {
        this.usetime = usetime;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4 == null ? null : ext4.trim();
    }

    public String getExt5() {
        return ext5;
    }

    public void setExt5(String ext5) {
        this.ext5 = ext5 == null ? null : ext5.trim();
    }

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
}