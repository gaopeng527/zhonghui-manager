package com.zhonghui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 展示商品规格参数Controller
 * @author DELL
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import com.zhonghui.service.ItemParamItemService;
@Controller
public class ItemParamItemController {
	
	@Autowired
	ItemParamItemService itemParamItemService;
	
	@RequestMapping("/showitem/{itemId}")
	public String showItemParam(@PathVariable Long itemId, Model model) {
		String itemParam = itemParamItemService.getItemParamByItemId(itemId);
		model.addAttribute("itemParam", itemParam);
		return "item";
	}
}
