package com.zhonghui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhonghui.common.pojo.EUTreeNode;
import com.zhonghui.service.ItemCatService;

/**
 * 商品分类管理Controller
 * @author DELL
 *
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getCatList(@RequestParam(value="id", defaultValue="0") Long parentId) {
		List<EUTreeNode> result = itemCatService.getCatList(parentId);
		return result;
	}
	
}
