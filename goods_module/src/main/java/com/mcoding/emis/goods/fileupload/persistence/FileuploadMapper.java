package com.mcoding.emis.goods.fileupload.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mcoding.emis.goods.common.persistence.BaseMapper;
import com.mcoding.emis.goods.fileupload.bean.Fileupload;

public interface FileuploadMapper extends BaseMapper<Fileupload>{
	public Fileupload getAFileByFullPath(String path);
	
	public List<Fileupload> queryShareFileList();
	
	public ArrayList<Fileupload> queryFileuploadPage(Map<String, Object> param);
	
    List<Fileupload> queryAllShareFileByPage(Map<String, Object> params);
}