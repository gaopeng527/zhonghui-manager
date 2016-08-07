package com.zhonghui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhonghui.common.pojo.EUTreeNode;
import com.zhonghui.common.pojo.ZhonghuiResult;
import com.zhonghui.service.ContentCategoryService;

/**
 * 内容分类管理
 * @author DELL
 *
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	
	@Autowired
	ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContentCatList(@RequestParam(value="id", defaultValue="0") Long parentId) {
		List<EUTreeNode> list = contentCategoryService.getCategoryList(parentId);
		return list;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public ZhonghuiResult createContentCategory(Long parentId, String name) {
		ZhonghuiResult result = contentCategoryService.insertContentCategory(parentId, name);
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ZhonghuiResult deleteContentCategory(Long parentId, Long id) {
		ZhonghuiResult result = contentCategoryService.deleteContentCategory(parentId, id);
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ZhonghuiResult deleteContentCategory(Long id, String name) {
		ZhonghuiResult result = contentCategoryService.updateContentCategory(id, name);
		return result;
	}
}
