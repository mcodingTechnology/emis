package com.mcoding.emis.goods.income.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="income_product")
public class IncomeProduct implements Serializable {
    private Integer id;

    private Integer productid;

    private Integer channelsid; //渠道商的id

    private Integer micromallprice;

    private Integer level1;

    private Integer level2;

    private Integer level3;

    private Integer level4;

    @ApiModelProperty("级别1 返利积分")
    private Integer level1point;

    @ApiModelProperty("级别2 返利积分")
    private Integer level2point;

    @ApiModelProperty("级别3 返利积分")
    private Integer level3point;

    @ApiModelProperty("级别4 返利积分")
    private Integer level4point;

    private String productName;//非字段值

    private Integer level5;

    private Integer level6;

    private Integer level7;

    private Integer level8;

    private Integer level9;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public  String dealerName; //渠道商名称
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getChannelsid() {
        return channelsid;
    }

    public void setChannelsid(Integer channelsid) {
        this.channelsid = channelsid;
    }

    public Integer getMicromallprice() {
        return micromallprice;
    }

    public void setMicromallprice(Integer micromallprice) {
        this.micromallprice = micromallprice;
    }

    public Integer getLevel1() {
        return level1;
    }

    public void setLevel1(Integer level1) {
        this.level1 = level1;
    }

    public Integer getLevel2() {
        return level2;
    }

    public void setLevel2(Integer level2) {
        this.level2 = level2;
    }

    public Integer getLevel3() {
        return level3;
    }

    public void setLevel3(Integer level3) {
        this.level3 = level3;
    }

    public Integer getLevel4() {
        return level4;
    }

    public void setLevel4(Integer level4) {
        this.level4 = level4;
    }

    public Integer getLevel1point() {
        return level1point;
    }

    public void setLevel1point(Integer level1point) {
        this.level1point = level1point;
    }

    public Integer getLevel2point() {
        return level2point;
    }

    public void setLevel2point(Integer level2point) {
        this.level2point = level2point;
    }

    public Integer getLevel3point() {
        return level3point;
    }

    public void setLevel3point(Integer level3point) {
        this.level3point = level3point;
    }

    public Integer getLevel4point() {
        return level4point;
    }

    public void setLevel4point(Integer level4point) {
        this.level4point = level4point;
    }

    public Integer getLevel5() {
        return level5;
    }

    public void setLevel5(Integer level5) {
        this.level5 = level5;
    }

    public Integer getLevel6() {
        return level6;
    }

    public void setLevel6(Integer level6) {
        this.level6 = level6;
    }

    public Integer getLevel7() {
        return level7;
    }

    public void setLevel7(Integer level7) {
        this.level7 = level7;
    }

    public Integer getLevel8() {
        return level8;
    }

    public void setLevel8(Integer level8) {
        this.level8 = level8;
    }

    public Integer getLevel9() {
        return level9;
    }

    public void setLevel9(Integer level9) {
        this.level9 = level9;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
    
}