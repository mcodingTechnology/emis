package com.mcoding.emis.goods.product.controller;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.product.bean.ProductQrcodeTemplate;
import com.mcoding.emis.goods.product.bean.ProductQrcodeTemplateExample;
import com.mcoding.emis.goods.product.persistence.ProductQrcodeTemplateMapper;
import com.mcoding.emis.goods.product.service.ProductQrcodeTemplateService;
import com.mcoding.emis.member.bean.member.Member;
import com.mcoding.emis.member.service.member.MemberService;

@Controller
public class ProductQrcodeTemplateController {
	
	private static Logger logger = LoggerFactory.getLogger(ProductQrcodeTemplateController.class);

	@Autowired
	protected ProductQrcodeTemplateMapper productQrcodeTemplateMapper;
	
	@Autowired
	protected MemberService memberService;
	
	@Autowired
	protected ProductQrcodeTemplateService productQrcodeTemplateService;
	
	@RequestMapping("productQrcodeTemplate/create")
	@ResponseBody
	public JsonResult<String> create(@RequestBody ProductQrcodeTemplate productQrcodeTemplate, HttpServletRequest request){
		JsonResult<String> result = new JsonResult<>();
		try{
			if (productQrcodeTemplate.getProductId() == null 
					|| productQrcodeTemplate.getProductId() ==0) {
				throw new IllegalArgumentException("查不到关联的产品，请刷新重试");
			}
			this.transform(productQrcodeTemplate, request);
			this.productQrcodeTemplateMapper.insertSelective(productQrcodeTemplate);
			
			result.setMsg("ok");
			result.setStatus("00");
		}catch(Exception e){
			e.printStackTrace();
			result.setMsg(e.getMessage());
			result.setStatus("error");
		}
		
		return result;
	}
	
	@RequestMapping("productQrcodeTemplate/edit")
	@ResponseBody
	public JsonResult<String> edit(@RequestBody ProductQrcodeTemplate productQrcodeTemplate, HttpServletRequest request){
		JsonResult<String> result = new JsonResult<>();
		try{
			if (productQrcodeTemplate.getId() == null || productQrcodeTemplate.getId() == 0) {
				throw new IllegalArgumentException("没有id,没法更新");
			}
			
			if (productQrcodeTemplate.getProductId() == null 
					|| productQrcodeTemplate.getProductId() ==0) {
				throw new IllegalArgumentException("查不到关联的产品，请刷新重试");
			}
			
			this.transform(productQrcodeTemplate, request);
			this.productQrcodeTemplateMapper.updateByPrimaryKeySelective(productQrcodeTemplate);
			
			result.setMsg("ok");
			result.setStatus("00");
		}catch(Exception e){
			e.printStackTrace();
			result.setMsg(e.getMessage());
			result.setStatus("error");
		}
		
		return result;
	}
	
	@RequestMapping("productQrcodeTemplate/service/deleteById")
	@ResponseBody
	public JsonResult<String> deleteById(int id){
		JsonResult<String> result = new JsonResult<>();
		try {
			this.productQrcodeTemplateMapper.deleteByPrimaryKey(id);
			result.setMsg("ok");
			result.setStatus("00");
		}catch(Exception e){
			e.printStackTrace();
			result.setMsg(e.getMessage());
			result.setStatus("error");
		}
		return result;
		
	}
	
	private void transform(ProductQrcodeTemplate template, HttpServletRequest request){
		if (StringUtils.isBlank(template.getImageUrl())) {
			return;
		}
		
		String basePath = "";
		int port = request.getServerPort();
		if (port == 80) {
			basePath = request.getScheme()+"://"+request.getServerName()+ request.getContextPath()+"/";
		}else{
			basePath = request.getScheme()+"://"+request.getServerName()+ ":" + port + request.getContextPath()+"/";
		}
		
		int index = template.getImageUrl().indexOf(basePath);
		if (index < 0) {
			return;
		}
		
		String imageUrl = template.getImageUrl().substring(index + basePath.length());
		template.setImageUrl(imageUrl);
		
		String pathStr = this.getClass().getClassLoader().getResource("").getPath();//文件保存目录的基本路径
		pathStr = pathStr.replaceAll("WEB-INF/classes/", "");//处理路径，改为静态资源目录存储
		
		String imagePath = pathStr + imageUrl;
		template.setImagePath(imagePath);
	} 
	
	@RequestMapping("productQrcodeTemplate/toMainView")
	public ModelAndView toMainView(){
		ModelAndView view = new ModelAndView();
		view.setViewName("productQrcodeTpl/toMainView");
		return view;
	}
	
	@RequestMapping("productQrcodeTemplate/toAddView")
	public ModelAndView toAddView(int productId){
		ModelAndView view = new ModelAndView();
		view.setViewName("productQrcodeTpl/toAddView");
		view.addObject("productId", productId);
		return view;
	}
	
	@RequestMapping("productQrcodeTemplate/setIsEnableById")
	@ResponseBody
	public JsonResult<String> setIsEnableById(int id, int isEnable){
		JsonResult<String> result = new JsonResult<>();
		try{
			ProductQrcodeTemplate t = new ProductQrcodeTemplate();
			t.setId(id);
			t.setIsEnable(isEnable);
			this.productQrcodeTemplateMapper.updateByPrimaryKeySelective(t);
			
			result.setMsg("ok");
			result.setStatus("00");
		}catch(Exception e){
			e.printStackTrace();
			result.setMsg(e.getMessage());
			result.setStatus("error");
		}
		
		return result;
	}
	
	@RequestMapping("productQrcodeTemplate/toUpdateViewById")
	public ModelAndView toUpdateViewById(int id){
		ModelAndView view = new ModelAndView();
		view.setViewName("productQrcodeTpl/toAddView");
		
		ProductQrcodeTemplate productQrcodeTemplate = this.productQrcodeTemplateMapper.selectByPrimaryKey(id);
		view.addObject("productId", productQrcodeTemplate.getProductId());
		view.addObject("productQrcodeTemplate", productQrcodeTemplate);
		return view;
	}
	
	@RequestMapping("productQrcodeTemplate/toListView")
	public ModelAndView toListView(int productId){
		ModelAndView view = new ModelAndView();
		view.setViewName("productQrcodeTpl/toListView");
		view.addObject("productId", productId);
		return view;
	}
	
	@RequestMapping("productQrcodeTemplate/findByProductId")
	@ResponseBody
	public PageView<ProductQrcodeTemplate> findByProductId(int productId, String iDisplayStart, String iDisplayLength){
		PageView<ProductQrcodeTemplate> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		try{
			ProductQrcodeTemplateExample example = new ProductQrcodeTemplateExample();
			example.createCriteria().andProductIdEqualTo(productId);
			example.setPageView(pageView);
			example.setOrderByClause("sort_number DESC");

			List<ProductQrcodeTemplate> list = this.productQrcodeTemplateMapper.selectByExampleByPage(example);
			pageView.setQueryResult(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return pageView;
		
	}

	@RequestMapping("merriplusApi/productQrcodeTemplate/findByProductId")
	@ResponseBody
	public JsonResult<PageView<ProductQrcodeTemplate>> findAll(int productId, 
			@RequestParam(defaultValue="6", value="pageSize") Integer pageSize, 
			@RequestParam(defaultValue="1", value="pageNo") Integer pageNo) {
		JsonResult<PageView<ProductQrcodeTemplate>> result = new JsonResult<>();

		try {

			PageView<ProductQrcodeTemplate> pageView = new PageView<>(pageNo, pageSize);
			ProductQrcodeTemplateExample example = new ProductQrcodeTemplateExample();
			example.createCriteria().andProductIdEqualTo(productId).andIsEnableEqualTo(1);
			example.setPageView(pageView);
			example.setOrderByClause("sort_number DESC");

			List<ProductQrcodeTemplate> list = this.productQrcodeTemplateMapper.selectByExampleByPage(example);
			pageView.setQueryResult(list);
			result.setData(pageView);
			result.setMsg("ok");
			result.setStatus("00");

		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg(e.getMessage());
			result.setStatus("error");
		}

		return result;
	}
	
	/*private PageView<ProductQrcodeTemplate> findByProductIdByPage(PageView<ProductQrcodeTemplate> pageView,
			int productId) {
		
	}*/

	@RequestMapping("merriplusApi/productQrcodeTemplate/contactWithQrcode")
	@ResponseBody
	public JsonResult<String> contactWithQrcode(int templateId, String url, HttpServletRequest request, HttpServletResponse response) {
		String imageUrl = "resources/uploads/qrcode/qrcodeFail.jpg";
		logger.info("contact qrcode with url: " + url);
		try {
			String openid = (String) request.getSession().getAttribute("openid");
			String brandCode = (String) request.getSession().getAttribute("brandCode");

			Member member = this.memberService.queryMemberByOpenid(openid, brandCode);
			
			ProductQrcodeTemplate template = this.productQrcodeTemplateMapper.selectByPrimaryKey(templateId);
			if (template == null || template.getImagePath() == null) {
				throw new NullPointerException("模板图片已丢失，请重新选择");
			}
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			url = URLDecoder.decode(url, "UTF-8");
			imageUrl = this.productQrcodeTemplateService.writeQrcoderForTemplate(url, rootPath, member, template);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String schemem = request.getScheme();
		String serverName = request.getServerName();
		int serverPort = request.getServerPort();
		String contextPath = request.getContextPath();
		imageUrl =  schemem +"://" + serverName + ":" + serverPort + contextPath + "/" +imageUrl;
		
		JsonResult<String> result = new JsonResult<>();
		result.setMsg("ok");
		result.setData(imageUrl);
		result.setStatus("00");
		return result;
	}

}
