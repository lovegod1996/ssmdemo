package com.hdx.ssm.dao;


import com.hdx.ssm.po.ItemsCustom;
import com.hdx.ssm.po.ItemsQueryVo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ItemsMapperCustom {
    //商品查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)throws Exception;
}