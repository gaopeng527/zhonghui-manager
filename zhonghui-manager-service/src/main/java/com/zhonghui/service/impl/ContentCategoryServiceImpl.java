package com.zhonghui.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhong.mapper.TbContentCategoryMapper;
import com.huizhong.pojo.TbContentCategory;
import com.huizhong.pojo.TbContentCategoryExample;
import com.huizhong.pojo.TbContentCategoryExample.Criteria;
import com.zhonghui.common.pojo.EUTreeNode;
import com.zhonghui.common.pojo.ZhonghuiResult;
import com.zhonghui.service.ContentCategoryService;
/**
 * 内容分类管理Service
 * @author DELL
 *
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
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

	@Override
	public ZhonghuiResult insertContentCategory(long parentId, String name) {
		// 创建一个pojo
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		contentCategory.setParentId(parentId);
		// 状态，可选值，1（正常），2（删除）
		contentCategory.setStatus(1);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		// 添加记录
		contentCategoryMapper.insert(contentCategory);
		// 查看父节点的isParent列是否为true，如果不是true要改成true
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
		// 判断是否为true
		if(!parentCat.getIsParent()){
			parentCat.setIsParent(true);
			// 更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		// 返回结果
		return ZhonghuiResult.ok(contentCategory);
	}

	@Override
	public ZhonghuiResult deleteContentCategory(long parentId, long id) {
		// 删除id对应的节点
		contentCategoryMapper.deleteByPrimaryKey(id);
		// 查询parentId下是否还有子节点
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		if(list == null || list.size()==0){
			TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
			parent.setIsParent(false);
			contentCategoryMapper.updateByPrimaryKey(parent);
		}
		return ZhonghuiResult.ok();
	}

	@Override
	public ZhonghuiResult updateContentCategory(long id, String name) {
		// 根据id查询
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		contentCategory.setName(name);
		contentCategoryMapper.updateByPrimaryKey(contentCategory);
		return ZhonghuiResult.ok();
	}

}
