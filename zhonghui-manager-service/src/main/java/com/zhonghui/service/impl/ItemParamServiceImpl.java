package com.zhonghui.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhong.mapper.TbItemParamMapper;
import com.huizhong.pojo.TbItemParam;
import com.huizhong.pojo.TbItemParamExample;
import com.huizhong.pojo.TbItemParamExample.Criteria;
import com.zhonghui.common.pojo.ZhonghuiResult;
import com.zhonghui.service.ItemParamService;
/**
 * 商品参数模板
 * @author DELL
 *
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
	
	@Autowired
	private TbItemParamMapper itemParamMapper;
	@Override
	public ZhonghuiResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		// 判断查询结果
		if(list != null && list.size() > 0) {
			return ZhonghuiResult.ok(list.get(0));
		}
		return ZhonghuiResult.ok();
	}
	@Override
	public ZhonghuiResult insertItemParam(TbItemParam itemParam) {
		// 补全pojo
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		// 插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		return ZhonghuiResult.ok();
	}

}
