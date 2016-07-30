package com.zhonghui.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 上传图片处理
 * @author DELL
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhonghui.common.utils.JsonUtils;
import com.zhonghui.service.PictureService;
@Controller
public class PictureController {
	
	@Autowired
	private PictureService pictureService;
	
	// 为了兼容性(google可以，其他浏览器可能不行)，将返回的Map改为json字符串
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String pictureUpload(MultipartFile uploadFile) {
		Map result = pictureService.uploadPicture(uploadFile);
		// 为了保证功能的兼容性，需要把Result转换为json格式的字符串
		String json = JsonUtils.objectToJson(result);
		return json;
	}
}
