package com.mcoding.emis.member.bean.member;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by benfu on 2017/4/14.
 */
public class MemberJoinCount {
    @ApiModelProperty("下线数量")
    private String childrenMember;

    @ApiModelProperty("下级数量")
    private String childrenDealer;

    public String getChildrenMember() {
        return childrenMember;
    }

    public void setChildrenMember(String childrenMember) {
        this.childrenMember = childrenMember;
    }

    public String getChildrenDealer() {
        return childrenDealer;
    }

    public void setChildrenDealer(String childrenDealer) {
        this.childrenDealer = childrenDealer;
    }
}
