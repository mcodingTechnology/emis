package com.mcoding.emis.goods.mallGame.util;

import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.emis.goods.common.utils.GenerateShortUrlUtil;
import com.mcoding.emis.goods.wechat.bean.TemplateMessageRecord;
import com.mcoding.emis.goods.wechat.utils.WxMpTemplateMsgUtil;

public class MallgameStakeWechatUtil {

	public static void sendMsg(WxMpTemplateMsgUtil wxMpTemplateMsgUtil, String openid, String keyword1, String keyword2,
			String productid, String resultid) throws Exception {
		try {
			
			Store store = StoreUtils.getStoreFromThreadLocal();
			String domain = store.getStoreDomain().split("\\s*;\\s*")[0];
			
			String longUrl = String.format(
					"%1$s/GiftMall/order_add.html?productId=%2$s&productNum=1&resultid=%3$s&type=stakegift",
					domain, productid, resultid);

//			UrlShortResponse resp = ShortURLUtil.getShortUrl(longUrl);
//			String tinyUrl = resp.getTinyurl();
			String tinyUrl =GenerateShortUrlUtil.generateShortUrl(longUrl);
			System.out.println("=======tinyUrl===================="+tinyUrl);
			wxMpTemplateMsgUtil.sendWxMpTemplateMessageByType(TemplateMessageRecord.TEMPLATE_MESSAGE_TYPE_STAKE, openid, keyword1,
					keyword2, null, null, null, null, tinyUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
