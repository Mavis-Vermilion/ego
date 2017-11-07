package cn.ego.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.ego.service.PicUploadService;
import cn.ego.utils.DateUtils;
import cn.ego.utils.FtpUtil;
import cn.ego.utils.IDUtils;
/**
 * 图片上传的业务实现类
 * @author 起灬風了
 *
 */
@Service
public class PicUploadServiceImpl implements PicUploadService{

	//FTP服务器的ip
	@Value("${FTP_HOST}")
	private String FTP_HOST;
	//FTP服务器的端口
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	//上传的用户名
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	//上传的密码
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	//上传的基础路径
	@Value("${FTP_BASEPATH}")
	private String FTP_BASEPATH;
	//上传之后回显的URL
	@Value("${FTP_URL}")
	private String FTP_URL;
	
	@Override
	public Map<String, Object> PicUpload(MultipartFile uploadFile) {
		//源文件名称
		String sourceFilename = uploadFile.getOriginalFilename();
		//上传文件的路径  路劲根据时间创建文件夹
		String filePath=DateUtils.getCurrentDateTime("/yyyy/MM/dd/");
		//上传之后的文件名
		String filename=IDUtils.getImageName()+sourceFilename.substring(sourceFilename.lastIndexOf("."));
		
		Map<String, Object> map = new HashMap<>();
		try {
			boolean uploadResult = FtpUtil.uploadFile(FTP_HOST, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASEPATH, filePath, filename, uploadFile.getInputStream());
			//上传文件之后的地址
			String targetPath = FTP_URL+ filePath + filename;
			if(uploadResult) {
				map.put("error", 0);
				map.put("url", targetPath);//回馈上传之后的路径
			}else {
				map.put("error", 1);
				map.put("message", "上传失败");
			}
		} catch (IOException e) {
			e.printStackTrace();
			map.put("error", 1);
			map.put("message", "上传异常");
		}
		return map;
	}

}
