package com.mcoding.emis.goods.wechat.service.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.bean.dictionary.DicGroupItem;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.service.dictionary.DicGroupItemService;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.emis.goods.common.utils.StringHelper;
import com.mcoding.emis.goods.wechat.bean.WxUserTags;
import com.mcoding.emis.goods.wechat.bean.WxUserTagsExample;
import com.mcoding.emis.goods.wechat.persistence.WxUserTagsMapper;
import com.mcoding.emis.goods.wechat.service.WxUserTagsService;
import java.util.List;
import javax.annotation.Resource;

import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.service.member.MemberService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("wxUserTagsService")
public class WxUserTagsServiceImpl implements WxUserTagsService {
    @Resource
    protected WxUserTagsMapper wxUserTagsMapper;
    @Autowired
    protected DicGroupItemService dicGroupItemService;
    @Autowired
    protected StoreWxRefService storeWxRefService;


    @CacheEvict(value={"wxUserTags"}, allEntries=true)
    @Override
    public void addObj(WxUserTags t) {
        this.wxUserTagsMapper.insertSelective(t);
    }

    @CacheEvict(value={"wxUserTags"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.wxUserTagsMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"wxUserTags"}, allEntries=true)
    @Override
    public void modifyObj(WxUserTags t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.wxUserTagsMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="wxUserTags", key="'WxUserTagsService_' + #root.methodName + '_' +#id")
    @Override
    public WxUserTags queryObjById(int id) {
        return this.wxUserTagsMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="wxUserTags", key="'WxUserTagsService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<WxUserTags> queryAllObjByExample(WxUserTagsExample example) {
        return this.wxUserTagsMapper.selectByExample(example);
    }

    @Cacheable(value="wxUserTags", key="'WxUserTagsService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<WxUserTags> queryObjByPage(WxUserTagsExample example) {
        PageView<WxUserTags> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.wxUserTagsMapper.selectByExampleByPage(example));
        return pageView;
    }

    @Override
    public void makeWxuserTags(Member member,WxMpService wxMpService) throws WxErrorException {
        //若是加盟商给微信用户打标签
        if(member!=null && member.getOpenid()!=null && StringHelper.isNotBlank(member.getLevelName())){
            String[] openids = new String[1];
            openids[0] = member.getOpenid();
            DicGroupItem dicItem = dicGroupItemService.queryItems("member_tags","offline");
            Long tagid = Long.valueOf(dicItem.getValue());
            wxMpService.getUserTagService().batchTagging(tagid ,openids);

            WxUserTags wxUserTags = new WxUserTags();
            wxUserTags.setOpenid(member.getOpenid());
            wxUserTags.setTagId(Integer.valueOf(dicItem.getValue()));
            wxUserTags.setTagName(dicItem.getDescription());
            this.wxUserTagsMapper.insertSelective(wxUserTags);
        }
    }
}