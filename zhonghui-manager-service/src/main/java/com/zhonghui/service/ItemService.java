package com.zhonghui.service;

import com.huizhong.pojo.TbItem;
import com.zhonghui.common.pojo.EasyUIDataGridResult;

public interface ItemService {
	TbItem getItemById(long itemId);
	EasyUIDataGridResult getItemList(int page, int rows);
}
