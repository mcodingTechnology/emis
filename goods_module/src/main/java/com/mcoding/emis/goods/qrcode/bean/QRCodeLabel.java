package com.mcoding.emis.goods.qrcode.bean;

import java.io.Serializable;
import java.util.Date;

public class QRCodeLabel implements Serializable {

	/**邀请好友加关注的 临时二维码 key**/
    public static final String LABEL_INVITATION_KEY = "100";
    
    /**马拉松游戏 邀请好友加关注的 临时二维码 key 20161008 hzy**/
    public static final String LABEL_GAME_MARATHON = "101";

    /**邀请好友加关注的 永久二维码 key**/
    public static final String LABEL_PERQRCODE_INVITATION_KEY = "102";
    
    public static final Integer MAX_EXPRIED_DAY = 30;

    private static final long serialVersionUID = 1L;
    private Integer id;

    private String qrcodekey;

    private String qrcodename;

    private String imgurl;

    private String perimgurl;

    private Integer ext;

    private String ext1;

    private String ext2;

    private Date createdate;

    private Date updatedate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQrcodekey() {
        return qrcodekey;
    }

    public void setQrcodekey(String qrcodekey) {
        this.qrcodekey = qrcodekey == null ? null : qrcodekey.trim();
    }

    public String getQrcodename() {
        return qrcodename;
    }

    public void setQrcodename(String qrcodename) {
        this.qrcodename = qrcodename == null ? null : qrcodename.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public Integer getExt() {
        return ext;
    }

    public void setExt(Integer ext) {
        this.ext = ext;
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

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getPerimgurl() {
        return perimgurl;
    }

    public void setPerimgurl(String perimgurl) {
        this.perimgurl = perimgurl;
    }
}