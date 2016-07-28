package com.zhonghui.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huizhong.mapper.TbItemMapper;
import com.huizhong.pojo.TbItem;
import com.huizhong.pojo.TbItemExample;

public class TestPageHelper {
	@Test
	public void testPageHelper() {
		// 创建一个Spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		// 从Spring容器中获取Mapper代理对象
		TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);
		// 执行查询，并分页
		TbItemExample example = new TbItemExample();
		// 分页处理
		PageHelper.startPage(1, 10);
		List<TbItem> items = mapper.selectByExample(example);
		// 取商品列表
		for(TbItem item : items){
			System.out.println(item.getTitle());
		}
		// 取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(items);
		long total = pageInfo.getTotal();
		System.out.println("共有商品："+total);
	}
}
