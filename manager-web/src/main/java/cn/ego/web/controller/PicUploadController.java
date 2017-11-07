package cn.ego.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import cn.ego.service.PicUploadService;
/**
 * 文件上传的
 * @author 起灬風了
 *
 */
@Controller
@RequestMapping("/pic")
public class PicUploadController {

	@Autowired
	private PicUploadService picUploadService;
	
	/**
	 * 上传文件
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public String PicUpload(MultipartFile uploadFile) {
		Map<String, Object> map = picUploadService.PicUpload(uploadFile);
		return JSON.toJSONString(map);
	}
}
