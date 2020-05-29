package com.mcoding.emis.goods.product.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.utils.image.ImageUtils;
import com.mcoding.base.utils.qrcode.QrcodeUtils;
import com.mcoding.emis.goods.product.bean.ProductQrcodeTemplate;
import com.mcoding.emis.goods.product.service.ProductQrcodeTemplateService;
import com.mcoding.emis.goods.wechat.utils.EmisOauthUrlUtils;
import com.mcoding.emis.member.bean.member.Member;

@Service
public class ProductQrcodeTemplateServiceImpl implements ProductQrcodeTemplateService {
	
	@Autowired
	protected StoreWxRefService storeWxRefService;

	@Override
	public String writeQrcoderForTemplate(String url, String rootPath, Member member, ProductQrcodeTemplate template) throws Exception {
		String fileSaveFolderPath = "resources" + File.separator + "uploads" + File.separator + "qrcode" + File.separator + "product_link";
		
		int paddingTopPercent = 46;
		int paddingLeftPercent = 72;
		
		//获取图片的保存路径
		File templateFile = new File(template.getImagePath());
		BufferedImage templateImage = ImageIO.read(templateFile);
		
		File blankBarFile = new File(rootPath + fileSaveFolderPath, "qrcodebg.png");
		BufferedImage blankBar = ImageIO.read(blankBarFile);
		
		Map<String, String> params = new Hashtable<>();
		params.put(EmisOauthUrlUtils.PARAMS_MAP_KEY_OPEN_ID, member.getOpenid());
		params.put(EmisOauthUrlUtils.PARAMS_MAP_KEY_BRAND_CODE, member.getBrandCode());
		
		if(member.getChannelsId() != null){
			params.put(EmisOauthUrlUtils.PARAMS_MAP_KEY_CHANNELSID_CODE, member.getChannelsId().toString());
		}
		if(member.getCompanyId() != null){
			params.put(EmisOauthUrlUtils.PARAMS_MAP_KEY_COMPANY_TYPE, member.getCompanyId().toString());
		}
		params.put("productId", template.getProductId().toString());
		
		//二维码中包含的连接
		String oauthUrl = EmisOauthUrlUtils.createOauthUrlForOpenid(url, params, true);
		
//		String oauthUrl = EmisOauthUrlUtils.createOauthUrlForOpenid(url, openid, brandCode, channelId , companyId);
//		WxMpService wxMpService = EmisWxUtils.getWxMpServiceByBrandCode(brandCode);
		
		//生成二维码
		int qrcodeWidth = 257;
		BufferedImage qrcode = QrcodeUtils.writeQrcode(oauthUrl, "UTF-8", qrcodeWidth);
		
		//生成模板下面的二维码栏
		blankBar = ImageUtils.contactCenter(blankBar, qrcode, paddingTopPercent, paddingLeftPercent);
		
		//合并成新的图片
		BufferedImage newImage = ImageUtils.contactVertical(templateImage, blankBar);
		
		//建立保存路径
		String newFileNamePath = fileSaveFolderPath + File.separator + "member_" + member.getMemberId();
		File folder = new File(rootPath + newFileNamePath);
		if(!folder.exists()){
			folder.mkdirs();
		}
		
		String fileName = "qrcode_template_" + template.getId() + ".jpg";
		File file = new File(rootPath + newFileNamePath, fileName);
		ImageIO.write(newImage, "jpg", file);
		newFileNamePath = newFileNamePath.replaceAll("\\" + File.separator, "/");
		
		return newFileNamePath + "/" + fileName;
		
	}

}
