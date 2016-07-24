package com.zhonghui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhong.mapper.TbItemMapper;
import com.huizhong.pojo.TbItem;
import com.huizhong.pojo.TbItemExample;
import com.huizhong.pojo.TbItemExample.Criteria;
import com.zhonghui.service.ItemService;

/**
 * 商品管理Service
 * @author DELL
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
//		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		TbItemExample itemExample = new TbItemExample();
		// 添加查询条件
		Criteria criteria = itemExample.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> items = itemMapper.selectByExample(itemExample);
		if(items != null && items.size() > 0){
			TbItem item = items.get(0);
			return item;
		}
		return null;
	}

}
