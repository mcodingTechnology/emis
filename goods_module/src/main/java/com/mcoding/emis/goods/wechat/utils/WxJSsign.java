package com.mcoding.emis.goods.wechat.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

public class WxJSsign {
	
    public static void main(String[] args) {
//        String jsapi_ticket = "jsapi_ticket";
//        System.out.println(WxJSsign.create_nonce_str());
//        // 注意 URL 一定要动态获取，不能 hardcode
//        String url = "http://example.com";
//        Map<String, String> ret = sign(jsapi_ticket, url);
//        for (Map.Entry entry : ret.entrySet()) {
//            System.out.println(entry.getKey() + ", " + entry.getValue());
//        }
    };
    
    /**
     * 获取js签名
     * @param brandCode
     * @param url 调用JS接口页面的完整URL
     * @return
     */
    public static Map<String, String> signForBrand(String brandCode, String url) {
    	try {
//	    	String appiId= WxMpServiceUtils.getAccountConfigFromThreadLocal().getAppId();
//	    	String appSecret = WxMpServiceUtils.getAccountConfigFromThreadLocal().getAppSecret();
    		
    		Store store = StoreUtils.getStoreFromThreadLocal();
    		String appiId = SpringContextHolder.getOneBean(StoreWxRefService.class).queryWxAppidByStoreId(store.getId());
	    	
	    	WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceFromThreadLocal();
			String jsapi_ticket = wxMpService.getJsapiTicket(false);
	    	Map<String, String> signData = sign(jsapi_ticket, url);
	    	signData.put("appId", appiId);
	    	return signData;
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 针对微信 卡券的签名
     * @param nonce_str
     * @param jsapi_ticket
     * @param timestamp
     * @param url
     * @return
     */
    public static Map<String, String> signForCard(String api_ticket, String app_id, String location_id,
    		String times_tamp, String nonce_str, String card_id, String card_type){
    	//注意这里参数名必须全部小写，且必须有序
    	String signature = null;
    	ArrayList<String> m_param_to_sign = new ArrayList<String>();
    	m_param_to_sign.add(api_ticket);
    	m_param_to_sign.add(app_id);
    	m_param_to_sign.add(times_tamp);
    	m_param_to_sign.add(nonce_str);
    	if(StringUtils.isNotBlank(card_id)) m_param_to_sign.add(card_id);
    	if(StringUtils.isNotBlank(card_type)) m_param_to_sign.add(card_type);
    	if(StringUtils.isNotBlank(location_id)) m_param_to_sign.add(location_id);
    	
    	Collections.sort(m_param_to_sign);
    	
    	StringBuilder string_to_sign = new StringBuilder();
        for (String str : m_param_to_sign){
            string_to_sign.append(str);
        }
        
        try{
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            byte[] digest = crypt.digest(string_to_sign.toString().getBytes());
            StringBuilder str = new StringBuilder();
            for (byte b : digest)
            {
                String hv = Integer.toHexString(b & 0xFF); 
                if( hv.length() < 2 )
                    str.append("0");
                str.append(hv);
            }
            
            signature = str.toString();
//            crypt.reset();
//            crypt.update(string_to_sign.toString().getBytes("UTF-8"));
//            signature = byteToHex(crypt.digest());
            
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        
        Map<String, String> ret = new HashMap<String, String>();
    	
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", times_tamp);
        ret.put("cardSign", signature);
        ret.put("card_id", card_id);

        return ret;
    } 
    
    /**
     * 针对微信 js_sdk初始化所用的签名
     * @param nonce_str
     * @param jsapi_ticket
     * @param timestamp
     * @param url
     * @return
     */
    public static Map<String, String> signForJsSdk(String nonce_str, String jsapi_ticket, String timestamp,
    		String url){
    	//注意这里参数名必须全部小写，且必须有序
    	String signature = null;
        String string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        
        Map<String, String> ret = new HashMap<String, String>();
        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    public static Map<String, String> sign(String jsapi_ticket, String url) {
//    	Map<String, String> ret = new HashMap<String, String>();
//        String nonce_str = create_nonce_str();
//        String timestamp = create_timestamp();
//        return signForJsSdk(nonce_str, jsapi_ticket, timestamp, url);
    	Map<String, String> ret = new Hashtable<>();
    	try {
//			String jsapiTicket = WxMpServiceUtils.getWxMpServiceFromThreadLocal().getJsapiTicket();
//			WxJsapiSignature jsSign = WxMpServiceUtils.getWxMpServiceFromThreadLocal().createJsapiSignature(url);
    		WxMpService wxMpService = SpringContextHolder.getOneBean(StoreWxRefService.class).queryWxMpServiceByStoreId(StoreUtils.getStoreFromThreadLocal().getId());
    		WxJsapiSignature jsSign = wxMpService.createJsapiSignature(url);
			
			
			
//			jsapiParams.put("appId", WxMpServiceUtils.currentWxMpConfigStorage().getAppId());
//			jsapiParams.put("timestamp", String.valueOf(jsSign.getTimestamp()));
//			jsapiParams.put("nonceStr", jsSign.getNoncestr());
//			jsapiParams.put("signature", jsSign.getSignature());
//			jsapiParams.put("ticket", jsapiTicket);
			
			ret.put("url", url);
	        ret.put("jsapi_ticket", jsapi_ticket);
	        ret.put("nonceStr", jsSign.getNonceStr());
	        ret.put("timestamp",  String.valueOf(jsSign.getTimestamp()));
	        ret.put("signature", jsSign.getSignature());
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		
		
		return ret;
    	
    	
//        String string1;
//        String signature = "";
//
//        //注意这里参数名必须全部小写，且必须有序
//        string1 = "jsapi_ticket=" + jsapi_ticket +
//                  "&noncestr=" + nonce_str +
//                  "&timestamp=" + timestamp +
//                  "&url=" + url;
//        System.out.println(string1);
//
//        try
//        {
//            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
//            crypt.reset();
//            crypt.update(string1.getBytes("UTF-8"));
//            signature = byteToHex(crypt.digest());
//        }
//        catch (NoSuchAlgorithmException e)
//        {
//            e.printStackTrace();
//        }
//        catch (UnsupportedEncodingException e)
//        {
//            e.printStackTrace();
//        }
//
//        ret.put("url", url);
//        ret.put("jsapi_ticket", jsapi_ticket);
//        ret.put("nonceStr", nonce_str);
//        ret.put("timestamp", timestamp);
//        ret.put("signature", signature);
//
//        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
