package com.zhonghui.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhong.mapper.TbContentCategoryMapper;
import com.huizhong.pojo.TbContentCategory;
import com.huizhong.pojo.TbContentCategoryExample;
import com.huizhong.pojo.TbContentCategoryExample.Criteria;
import com.zhonghui.common.pojo.EUTreeNode;
import com.zhonghui.service.ContentCategoryService;
/**
 * 内容分类管理Service
 * @author DELL
 *
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		// 根据parentId查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EUTreeNode treeNode = new EUTreeNode();
			treeNode.setId(tbContentCategory.getId());
			treeNode.setText(tbContentCategory.getName());
			treeNode.setState(tbContentCategory.getIsParent() ? "closed":"open");
			resultList.add(treeNode);
		}
		return resultList;
	}

}
