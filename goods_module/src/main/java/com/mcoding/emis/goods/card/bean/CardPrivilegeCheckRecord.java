package com.mcoding.emis.goods.card.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="card_privilege_check_record")
public class CardPrivilegeCheckRecord implements Serializable {
    private Integer id;

    @ApiModelProperty("对应的卡券id")
    private Integer cardTypeId;

    private Integer cardId;

    private String cardName;

    @ApiModelProperty("卡券前缀")
    private String codePrefix;

    @ApiModelProperty("卡券后缀")
    private String codeSuffix;

    @ApiModelProperty("全券号")
    private String code;

    @ApiModelProperty("所属品牌")
    private String brandCode;

    @ApiModelProperty("客户id（旺旺号）")
    private String user;

    @ApiModelProperty("核销时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(Integer cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }

    public String getCodePrefix() {
        return codePrefix;
    }

    public void setCodePrefix(String codePrefix) {
        this.codePrefix = codePrefix == null ? null : codePrefix.trim();
    }

    public String getCodeSuffix() {
        return codeSuffix;
    }

    public void setCodeSuffix(String codeSuffix) {
        this.codeSuffix = codeSuffix == null ? null : codeSuffix.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode == null ? null : brandCode.trim();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}