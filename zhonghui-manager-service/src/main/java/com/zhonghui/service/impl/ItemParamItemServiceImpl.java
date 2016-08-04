package com.zhonghui.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhong.mapper.TbItemParamItemMapper;
import com.huizhong.pojo.TbItemParamItem;
import com.huizhong.pojo.TbItemParamItemExample;
import com.huizhong.pojo.TbItemParamItemExample.Criteria;
import com.zhonghui.common.utils.JsonUtils;
import com.zhonghui.service.ItemParamItemService;
/**
 * 商品规格参数展示
 * @author DELL
 *
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public String getItemParamByItemId(Long itemId) {
		// 根据商品id查询商品规格参数
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		// 执行查询
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if(list == null || list.size() == 0){
			return "";
		}
		// 取规格参数信息
		TbItemParamItem itemParamItem = list.get(0);
		String paramData = itemParamItem.getParamData();
		// 把规格参数json数据转换成java对象
		List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
		// 生成html
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\"\n");
		sb.append("	class=\"Ptable\">\n");
		sb.append("	<tbody>\n");
		for(Map m1 : jsonList) {
			sb.append("		<tr>\n");
			sb.append("			<th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
			sb.append("		</tr>\n");
			List<Map> list2 = (List<Map>) m1.get("params");
			for(Map m2 : list2) {
				sb.append("		<tr>\n");
				sb.append("			<td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
				sb.append("			<td>"+m2.get("v")+"</td>\n");
				sb.append("		</tr>\n");
			}	
		}
		sb.append("	</tbody>\n");
		sb.append("</table>");
		return sb.toString();
	}

}