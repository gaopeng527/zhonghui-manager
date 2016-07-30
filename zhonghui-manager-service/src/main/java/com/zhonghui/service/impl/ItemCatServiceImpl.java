package com.zhonghui.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhong.mapper.TbItemCatMapper;
import com.huizhong.pojo.TbItemCat;
import com.huizhong.pojo.TbItemCatExample;
import com.huizhong.pojo.TbItemCatExample.Criteria;
import com.zhonghui.common.pojo.EUTreeNode;
import com.zhonghui.service.ItemCatService;
/**
 * 商品分类管理
 * @author DELL
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public List<EUTreeNode> getCatList(long parentId) {
		// 创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 根据条件查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		// 把列表转换为EUTreeNode list
		List<EUTreeNode> resultList = new ArrayList<>();
		for(TbItemCat itemCat : list){
			EUTreeNode treeNode = new EUTreeNode();
			treeNode.setId(itemCat.getId());
			treeNode.setText(itemCat.getName());
			treeNode.setState(itemCat.getIsParent() ? "closed":"open");
			resultList.add(treeNode);
		}
		// 返回结果
		return resultList;
	}

}
