package com.zhonghui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huizhong.pojo.TbContent;
import com.zhonghui.common.pojo.ZhonghuiResult;
import com.zhonghui.service.ContentService;

/**
 * 内容管理Controller
 * @author DELL
 *
 */
@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/save")
	@ResponseBody
	public ZhonghuiResult insertContent(TbContent content) {
		ZhonghuiResult result = contentService.insertContent(content);
		return result;
	}
}
