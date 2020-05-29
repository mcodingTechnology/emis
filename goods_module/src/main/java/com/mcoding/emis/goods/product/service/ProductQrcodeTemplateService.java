package com.mcoding.emis.goods.product.service;

import com.mcoding.emis.goods.product.bean.ProductQrcodeTemplate;
import com.mcoding.emis.member.bean.member.Member;

public interface ProductQrcodeTemplateService {
	
	/***
	 * 为会员输出 产品二维码的合成图
	 * @param member
	 * @param template
	 * @param outputStream
	 * @throws Exception 
	 */
	public String writeQrcoderForTemplate(String url, String savePath, Member member, ProductQrcodeTemplate template) throws Exception;

}
