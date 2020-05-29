package com.mcoding.emis.goods.fileupload.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.emis.goods.fileupload.bean.Fileupload;
import com.mcoding.emis.goods.fileupload.persistence.FileuploadMapper;
import com.mcoding.emis.goods.fileupload.service.FileuploadService;
import com.mcoding.emis.member.common.CommonResult;

/**
 * @author Administrator
 *
 */
@Service
public class FileuploadServiceImpl implements FileuploadService {
    private static final Logger log = Logger.getLogger(FileuploadServiceImpl.class);

    @Autowired
    private FileuploadMapper fileuploadMapper;

	@Override
	public CommonResult<String> addObj(Fileupload t) {
        CommonResult<String> result = new CommonResult<String>();
        try {
        	fileuploadMapper.add(t);
            //event.setUserId(XRContextUtil.getLoginUserId());
            result.setCode(0);
            result.setMsg("ok");
        } catch (Exception e) {
            result.setCode(1);
            result.setMsg(e.getMessage());
            log.error("添加上传文件出现异常：", e);
        }
        return result;
	}

	@Override
	public CommonResult<String> deleteObjById(String id) {
        CommonResult<String> result = new CommonResult<String>();
        try {
        	fileuploadMapper.delete(id);
            //event.setUserId(XRContextUtil.getLoginUserId());
            result.setCode(0);
            result.setMsg("ok");
        } catch (Exception e) {
            result.setCode(1);
            result.setMsg(e.getMessage());
            log.error("删除上传文件出现异常：", e);
        }
        return result;
	}

	@Override
	public CommonResult<String> modifyObj(Fileupload t) {
        CommonResult<String> result = new CommonResult<String>();
        try {
        	//String pathString = t.getUpFullpath().replace("/pms/", "");
        	String pathString = t.getUpFullpath();
	    	String filePath = pathString.substring(pathString.indexOf("resources"), pathString.length());
	    	Fileupload fileupload = fileuploadMapper.getAFileByFullPath(filePath);
        	
        	fileupload.setIsShare(t.getIsShare());
        	fileupload.setUpTitle(t.getUpTitle());
        	fileupload.setUpDatatime(new Date());
        	fileuploadMapper.update(fileupload);
            //event.setUserId(XRContextUtil.getLoginUserId());
            result.setCode(0);
            result.setMsg("ok");
        } catch (Exception e) {
            result.setCode(1);
            result.setMsg(e.getMessage());
            log.error("修改上传文件出现异常：", e);
        }
        return result;
	}

	@Override
	public CommonResult<Fileupload> queryObjById(String id) {
        CommonResult<Fileupload> result = new CommonResult<Fileupload>();
        try {
            //event.setUserId(XRContextUtil.getLoginUserId());
            result.setData(fileuploadMapper.queryById("1"));
        	result.setCode(0);
            result.setMsg("ok");
        } catch (Exception e) {
            result.setCode(1);
            result.setMsg(e.getMessage());
            log.error("查询上传文件出现异常：", e);
        }
        return result;
	}

	@Override
	public CommonResult<ArrayList<Fileupload>> queryListObj(String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<PageView<Fileupload>> queryObjByPage(int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<Fileupload> queryObjByPage(String iDisplayStart,
			String iDisplayLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fileupload getAFileByFullPath(String path) {
		// TODO Auto-generated method stub
		return fileuploadMapper.getAFileByFullPath(path);
	}

	@Override
	public List<Fileupload> queryShareFileList() {
		// TODO Auto-generated method stub
		return fileuploadMapper.queryShareFileList();
	}

	@Override
	public PageView<Fileupload> queryFileuploadPage(Map<String, Object> param) {
		PageView<Fileupload> result=new PageView<Fileupload>();
		result.setQueryResult(fileuploadMapper.queryFileuploadPage(param));
		return result;
	}
	
	@Override
	public PageView<Fileupload> queryAllShareFileByPage(String iDisplayStart,
			String iDisplayLength, String sSearch) {
		PageView<Fileupload> pageView = new PageView<Fileupload>(iDisplayStart, iDisplayLength);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pageView", pageView);
        params.put("upTitle", sSearch);
        List<Fileupload> data = fileuploadMapper.queryAllShareFileByPage(params);
        pageView.setQueryResult(data);
        return pageView;
	}
    

}
