package com.mcoding.emis.member.service.member;

import com.mcoding.base.core.BaseService;
import com.mcoding.emis.member.bean.member.MemberAddress;
import com.mcoding.emis.member.bean.member.MemberAddressExample;

public interface EmisMemberAddressService extends BaseService<MemberAddress, MemberAddressExample> {

    public void deleteObjByExample(MemberAddressExample example);

    public void modifyObj(MemberAddress t, MemberAddressExample example);
}