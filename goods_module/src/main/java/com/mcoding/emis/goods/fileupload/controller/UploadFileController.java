package com.mcoding.emis.goods.fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.auth.utils.SpringSecurityUtils;
import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.utils.BasePath;
import com.mcoding.emis.goods.fileupload.bean.Fileupload;
import com.mcoding.emis.goods.fileupload.service.FileuploadService;
import com.mcoding.emis.member.common.CommonResult;


@Controller
public class UploadFileController {
	
	private Fileupload fileupload;

	@Autowired
	private FileuploadService fileuploadService;
	
	/*
	 * 			DEMO  -- Kindeditor调用方法 -- Moshow
	 * 
	 * ①'uploadFile.html?folderName=wechat_replay'
	 * ②'fileManager.html?folderName=wechat_replay'
	 * 需要附加folderName作为区分，则会上传到对应的D:/web/yueshenghuo_image/folderName目录
	 * -----------------------------------------------------------------------------------------
	 * var editor = K.editor({
	 *							allowFileManager : true,
	 *							cssPath : '../plugins/code/prettify.css',
	 *							uploadJson : 'uploadFile.html?folderName=wechat_replay',
	 *							fileManagerJson : 'fileManager.html?folderName=wechat_replay'
	 *						});
	 * ------------------------------------------------------------------------------------------
	 * 
	 * */
	
	 
	 /**
	  * 获取WebRoot物理路径
	  * @author Moshow
	  */ 
	 public String getPhypath(Integer type){
	  String pathStr = this.getClass().getClassLoader().getResource("").getPath();
	  // 对 Windows下获取 物理路径做特殊处理
	  if("\\".equals(File.separator)) {
	   pathStr = pathStr.substring(1).replaceAll("/", "\\\\");
	   pathStr = pathStr.replaceAll("%20", " ");
	   switch (type) {
	   case 1:
	    pathStr = pathStr.replaceAll("WEB-INF\\\\classes\\\\", "resources\\\\uploads\\\\");
	    break;
	   case 2:
	    pathStr = pathStr.replaceAll("build\\\\classes\\\\", "WebRoot\\\\resources\\\\uploads\\\\");
	    break;
	   default:
	    pathStr = pathStr.replaceAll("WEB-INF\\\\classes\\\\", "resources\\\\uploads\\\\");
	    break;
	   }
	  }
	  return pathStr;
	 }

	/**
	 * Kindeditor所需方法uploadJson
	 * @param folderName保存的文件夹名称
	 * @author Benson
	 */
	@RequestMapping("uploadFile")
	@ResponseBody
	public void uploadJson(HttpSession session,HttpServletRequest request,
			HttpServletResponse response,String fileId,String floder) throws IOException,
			FileUploadException {
		String pathStr = this.getClass().getClassLoader().getResource("").getPath();//文件保存目录的基本路径
		pathStr = pathStr.replaceAll("WEB-INF/classes/", "resources/uploads/");//处理路径，改为静态资源目录存储
		//获取URL地址
		String basePath = "";
		int port = request.getServerPort();
		if (port == 80) {
			basePath = request.getScheme()+"://"+request.getServerName()+ request.getContextPath()+"/";
		}else{
			basePath = request.getScheme()+"://"+request.getServerName()+ ":" + port + request.getContextPath()+"/";
		}
		
		System.out.println("basePath======"+basePath);
		String filePhyPath = pathStr +fileId+"/";
		// 文件保存目录路径
		String savePath = filePhyPath;
		PrintWriter out = response.getWriter();
		// 文件保存目录URL
		String saveUrl = basePath+"resources/uploads"+"/"+fileId+"/";
		//String rootUrl=request.getServletPath();

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 最大文件大小
		long maxSize = 100000000;

		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			out.println(getError("请选择文件。"));
			return;
		}
		// 检查目录
		File uploadDir = new File(savePath);
		uploadDir.mkdirs();
		if (!uploadDir.isDirectory()) {
			out.println(getError("上传目录不存在。"));
			return;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			out.println(getError("上传目录没有写权限。"));
			return;
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			out.println(getError("目录名不正确。"));
			return;
		}
		// 创建文件夹
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();
			long fileSize = mf.getSize();
			String fileName = mf.getOriginalFilename();
			// 检查文件大小
			if (fileSize > maxSize) {
				out.println(getError("上传文件大小超过限制。"));
				return;
			}
			// 检查扩展名
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
					.toLowerCase();
			if (!(Arrays.asList(extMap.get(dirName).split(","))
					.contains(fileExt))) {
				if(!"file".equals(dirName)){
					out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(dirName) + "格式。"));
					return;
				}
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String newFileName = df.format(new Date()) + "_"
					+ new Random().nextInt(9999) + "." + fileExt;
			saveUrl=saveUrl+newFileName;
			//String fileSavePath = request.getScheme()+"://"+request.getServerName()+ request.getContextPath()+"/"+"resources/uploads"+"/"+fileId+"/"+newFileName;
			//String fileSavePath = basePath+"resources/uploads"+"/"+fileId+"/"+newFileName;
			String fileSavePath = basePath+"resources/uploads"+"/"+fileId+"/"+newFileName;
			System.out.println("fileSavePath===="+fileSavePath);
			try {
				File uploadedFile = new File(savePath, newFileName);
				FileCopyUtils.copy(mf.getBytes(), uploadedFile);
			} catch (Exception e) {
				out.println(getError("上传文件失败。"));
				return;
			}
			fileupload=new Fileupload();
			fileupload.setUpDatatime(new Date());
			fileupload.setUpFullpath(fileSavePath);
			fileupload.setUpTitle(fileName);
			fileupload.setUpType(dirName);
			//fileupload.setUpUserId(fileId);
			fileuploadService.addObj(fileupload);
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", fileSavePath);
			obj.put("title", fileName);
			System.out.println("url================"+fileSavePath);
			//解决报错：in a frame because it set 'X-Frame-Options' to 'DENY'.
			response.setHeader("X-Frame-Options", "SAMEORIGIN");// 解决IFrame拒绝的问题  
			out.println(obj.toJSONString());
			System.out.println("obj.toJSONString()================"+obj.toJSONString());
		}
	}

	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}

	/**
	 * Kindeditor所需方法fileManager
	 * @param folderName保存的文件夹名称
	 * @author Moshow
	 */
	@RequestMapping("fileManager")
	@ResponseBody
	public void fileManager(HttpSession session,HttpServletRequest request,
			HttpServletResponse response,String fileId,String floder) throws IOException {
		String pathStr = this.getClass().getClassLoader().getResource("").getPath();//文件保存目录的基本路径
		pathStr = pathStr.replaceAll("WEB-INF/classes/", "resources/uploads/");//处理路径，改为静态资源目录存储
		String rootPath =pathStr+"/"+fileId+"/";
		PrintWriter out = response.getWriter();
		// 根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
		String rootUrl = BasePath.getBasePath(request)+"resources/uploads"+"/"+fileId+"/";

		// 图片扩展名
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };

		String dirName = request.getParameter("dir");
		if (dirName != null) {
			if (!Arrays.<String> asList(
					new String[] { "image", "flash", "media", "file" })
					.contains(dirName)) {
				out.println("Invalid Directory name.");
				return;
			}
			File saveDirFile = new File(rootPath);
			saveDirFile.mkdirs();
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		// 根据path参数，设置各路径和URL
		String path = request.getParameter("path") != null ? request
				.getParameter("path") : "";
				String currentPath = rootPath + path;
				String currentUrl = rootUrl + path;
				String currentDirPath = path;
				String moveupDirPath = "";
				if (!"".equals(path)) {
					String str = currentDirPath.substring(0,
							currentDirPath.length() - 1);
					moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0,
							str.lastIndexOf("/") + 1) : "";
				}

				// 排序形式，name or size or type
				String order = request.getParameter("order") != null ? request
						.getParameter("order").toLowerCase() : "name";

						// 不允许使用..移动到上一级目录
						if (path.indexOf("..") >= 0) {
							out.println("Access is not allowed.");
							return;
						}
						// 最后一个字符不是/
						if (!"".equals(path) && !path.endsWith("/")) {
							out.println("Parameter is not valid.");
							return;
						}
						// 目录不存在或不是目录
						File currentPathFile = new File(currentPath);
						if (!currentPathFile.isDirectory()) {
							out.println("Directory does not exist.");
							return;
						}

						// 遍历目录取的文件信息
						List<Hashtable> fileList = new ArrayList<Hashtable>();
						if (currentPathFile.listFiles() != null) {
							for (File file : currentPathFile.listFiles()) {
								Hashtable<String, Object> hash = new Hashtable<String, Object>();
								String fileName = file.getName();
								if (file.isDirectory()) {
									hash.put("is_dir", true);
									hash.put("has_file", (file.listFiles() != null));
									hash.put("filesize", 0L);
									hash.put("is_photo", false);
									hash.put("filetype", "");
								} else if (file.isFile()) {
									String fileExt = fileName.substring(
											fileName.lastIndexOf(".") + 1).toLowerCase();
									hash.put("is_dir", false);
									hash.put("has_file", false);
									hash.put("filesize", file.length());
									hash.put("is_photo", Arrays.<String> asList(fileTypes)
											.contains(fileExt));
									hash.put("filetype", fileExt);
								}
								hash.put("filename", fileName);
								hash.put("datetime",
										new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file
												.lastModified()));
								fileList.add(hash);
							}
						}

						if ("size".equals(order)) {
							Collections.sort(fileList, new SizeComparator());
						} else if ("type".equals(order)) {
							Collections.sort(fileList, new TypeComparator());
						} else {
							Collections.sort(fileList, new NameComparator());
						}
						JSONObject result = new JSONObject();
						result.put("moveup_dir_path", moveupDirPath);
						result.put("current_dir_path", currentDirPath);
						result.put("current_url", currentUrl);
						result.put("total_count", fileList.size());
						result.put("file_list", fileList);

						response.setContentType("application/json; charset=UTF-8");
						out.println(result.toJSONString());
	}

	public class NameComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filename"))
						.compareTo((String) hashB.get("filename"));
			}
		}
	}

	public class SizeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long) hashA.get("filesize")) > ((Long) hashB
						.get("filesize"))) {
					return 1;
				} else if (((Long) hashA.get("filesize")) < ((Long) hashB
						.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	public class TypeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filetype"))
						.compareTo((String) hashB.get("filetype"));
			}
		}
	}
	
	@RequestMapping("/fileUploadView.html")
    public ModelAndView fileUploadView() {
        ModelAndView mav = new ModelAndView();
        CommonResult<Fileupload> fileupload = fileuploadService.queryObjById("1");
    	Fileupload f = fileupload.getData();
        mav.addObject("f", f);
        mav.setViewName("fileupload/fileUploadView");
        return mav;
    }
	
	@RequestMapping("/fileCenterList.html")
    public ModelAndView fileCenterList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("fileupload/fileCenterList");
        return mav;
    }
	
	/**
	 * 删除文件、文件夹
	 */
	@RequestMapping("/deleteFile")
	@ResponseBody
	public boolean deleteFile(String filename,Integer userId) {
		//获取上传用户名
		userId=SpringSecurityUtils.getLoginUserId();
		String filePhyPath = getPhypath(1)+"\\\\"+userId+"\\\\"+filename;
		File file = new File(filePhyPath);
		if(file!=null&&!file.isDirectory()){
			//删除单个文件
			file.delete();
		}
		return true;
	}
	
    /**
     * @Title: addShareFile
     * @Description: 添加共享文件
     * @param: @param fileupload
     * @param: @return   
     * @return: CommonResult<String>
     * @author: Benson   
     * @throws
     */
    @ResponseBody
    @RequestMapping("/addShareFile")
    public CommonResult<String> addShareFile(Fileupload fileupload) {
        return fileuploadService.modifyObj(fileupload);
    }
    
    /**
     * @Title: loadShareFileData
     * @Description: 获取共享文件列表
     * @param: @param fileupload
     * @param: @return   
     * @return: CommonResult<String>
     * @author: Benson   
     * @throws
     */
    @ResponseBody
    @RequestMapping("/loadShareFileData")
    public List<Fileupload> loadShareFileData() {
        return fileuploadService.queryShareFileList();
    }
    
    /**
     * @Title: queryAllShareFileData
     * @Description: 获取共享文件列表
     * @param: @param fileupload
     * @param: @return   
     * @return: CommonResult<String>
     * @author: Benson   
     * @throws
     */
    @ResponseBody
    @RequestMapping("/queryAllShareFileByPage")
    public PageView<Fileupload> queryAllShareFileByPage(String iDisplayStart, String iDisplayLength, String sSearch) {
        return fileuploadService.queryAllShareFileByPage(iDisplayStart, iDisplayLength,sSearch);
    }

	/**   
	* @Title: UploadFileController.java 
	* @Description: 文件上传列表
	* @author moshow 
	* @date 2014年9月4日 
	* @version V1.0   
	*/ 
	@RequestMapping("/fileUploadList.html")
    public ModelAndView fileUploadList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("fileupload/fileUploadList");
        return mav;
    }
	
    @RequestMapping("/queryfileUploadData")
    @ResponseBody
    public PageView<Fileupload> queryfileUploadData(Integer userId,Map<String, Object> params,String summaryType,String sSearch, String iDisplayStart, String iDisplayLength,String startDate,String endDate) {
    	params = new HashMap<String, Object>();
    	userId=SpringSecurityUtils.getLoginUserId();
    	if (iDisplayStart!=null&&iDisplayStart!="") {
    		iDisplayStart="0";
    		params.put("iDisplayStart", Integer.parseInt(iDisplayStart));
		}
    	if (iDisplayLength!=null&&iDisplayLength!="") {
    		iDisplayStart="10";
    		params.put("iDisplayLength", Integer.parseInt(iDisplayLength));
		}
    	params.put("userId", userId);
        params.put("sSearch", sSearch);
        return fileuploadService.queryFileuploadPage(params);
    }
}