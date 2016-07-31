package com.zhonghui.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huizhong.pojo.TbItem;
import com.zhonghui.common.pojo.EasyUIDataGridResult;
import com.zhonghui.common.pojo.ZhonghuiResult;
import com.zhonghui.service.ItemService;

/**
 * 商品管理Controller
 * @author DELL
 *
 */
@Controller
public class ItemController {
	@Resource
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId){
		return itemService.getItemById(itemId);
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		return itemService.getItemList(page, rows);
	}
	
	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	public ZhonghuiResult createItem(TbItem item, String desc) throws Exception {
		ZhonghuiResult result = itemService.createItem(item, desc);
		return result;
	}
}
