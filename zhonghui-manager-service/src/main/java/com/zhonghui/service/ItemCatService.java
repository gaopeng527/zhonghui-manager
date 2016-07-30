package com.zhonghui.service;

import java.util.List;

import com.zhonghui.common.pojo.EUTreeNode;

public interface ItemCatService {
	List<EUTreeNode> getCatList(long parentId);
}
