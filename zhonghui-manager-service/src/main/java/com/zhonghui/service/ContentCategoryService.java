package com.zhonghui.service;

import java.util.List;

import com.zhonghui.common.pojo.EUTreeNode;

public interface ContentCategoryService {
	List<EUTreeNode> getCategoryList(long parentId);
}
