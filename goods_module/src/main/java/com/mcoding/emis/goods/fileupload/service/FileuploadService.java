package com.mcoding.emis.goods.fileupload.service;

import java.util.List;
import java.util.Map;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.common.service.BaseService;
import com.mcoding.emis.goods.fileupload.bean.Fileupload;

public interface FileuploadService extends BaseService<Fileupload, String> {
	public Fileupload getAFileByFullPath(String path);
	
	public List<Fileupload> queryShareFileList();//查询共享文件
	
	public PageView<Fileupload> queryAllShareFileByPage(String iDisplayStart,
			String iDisplayLength, String sSearch);//查询所有共享文件
	
	public PageView<Fileupload> queryFileuploadPage(Map<String, Object> param);
}
