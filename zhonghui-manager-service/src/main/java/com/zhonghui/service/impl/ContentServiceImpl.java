package com.zhonghui.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhong.mapper.TbContentMapper;
import com.huizhong.pojo.TbContent;
import com.zhonghui.common.pojo.ZhonghuiResult;
import com.zhonghui.service.ContentService;
/**
 * 内容管理Service
 * @author DELL
 *
 */
@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper contentMapper;
	
	@Override
	public ZhonghuiResult insertContent(TbContent content) {
		// 补全pojo内容
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		return ZhonghuiResult.ok();
	}
	
}
