package com.zhonghui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huizhong.pojo.TbItemParam;
import com.zhonghui.common.pojo.ZhonghuiResult;
import com.zhonghui.service.ItemParamService;

/**
 * 商品规格参数Controller管理
 * @author DELL
 *
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public ZhonghuiResult getItemParamByCid(@PathVariable Long itemCatId) {
		ZhonghuiResult result = itemParamService.getItemParamByCid(itemCatId);
		return result;
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public ZhonghuiResult insertItemParam(@PathVariable Long cid, String paramData) {
		// 创建pojo对象
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		ZhonghuiResult result = itemParamService.insertItemParam(itemParam);
		return result;
	}
	
}
