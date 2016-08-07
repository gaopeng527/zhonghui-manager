package com.zhonghui.service;

import java.util.List;

import com.zhonghui.common.pojo.EUTreeNode;
import com.zhonghui.common.pojo.ZhonghuiResult;

public interface ContentCategoryService {
	List<EUTreeNode> getCategoryList(long parentId);
	ZhonghuiResult insertContentCategory(long parentId, String name);
	ZhonghuiResult deleteContentCategory(long parentId, long id);
}
