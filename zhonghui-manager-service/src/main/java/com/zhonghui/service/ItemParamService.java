package com.zhonghui.service;

import com.huizhong.pojo.TbItemParam;
import com.zhonghui.common.pojo.ZhonghuiResult;

public interface ItemParamService {
	ZhonghuiResult getItemParamByCid(long cid);
	ZhonghuiResult insertItemParam(TbItemParam itemParam);
}
