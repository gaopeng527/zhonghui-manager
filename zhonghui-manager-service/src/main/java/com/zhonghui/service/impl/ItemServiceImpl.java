package com.zhonghui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huizhong.mapper.TbItemMapper;
import com.huizhong.pojo.TbItem;
import com.huizhong.pojo.TbItemExample;
import com.huizhong.pojo.TbItemExample.Criteria;
import com.zhonghui.common.pojo.EasyUIDataGridResult;
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

	/**
	 * 商品列表查询
	 */
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		// 查询商品列表
		TbItemExample example = new TbItemExample();
		// 分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> items = itemMapper.selectByExample(example);
		// 创建一个返回值对象
		EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
		easyUIDataGridResult.setRows(items);
		// 取记录总条目数
		PageInfo<TbItem> pageInfo = new PageInfo<>(items);
		long total = pageInfo.getTotal();
		easyUIDataGridResult.setTotal(total);
		return easyUIDataGridResult;
	}

}
