package com.zhonghui.service.impl;

import java.util.Date;
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
import com.zhonghui.common.pojo.ZhonghuiResult;
import com.zhonghui.common.utils.IDUtils;
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

	@Override
	public ZhonghuiResult createItem(TbItem item) {
		// item补全
		// 生成商品ID
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		// 设置商品状态，新添商品为正常，1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		// 商品创建时间
		item.setCreated(new Date());
		// 商品更新时间
		item.setUpdated(new Date());
		// 插入到数据库
		itemMapper.insert(item);
		return ZhonghuiResult.ok();
	}

}
