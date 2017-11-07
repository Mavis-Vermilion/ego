package cn.ego.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface PicUploadService {

	Map<String,Object> PicUpload(MultipartFile uploadFile);
}
