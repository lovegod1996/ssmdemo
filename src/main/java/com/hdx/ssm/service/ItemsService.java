package com.hdx.ssm.service;

import com.hdx.ssm.po.ItemsCustom;
import com.hdx.ssm.po.ItemsQueryVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *商品管理service
 * Author: lovegod
 * Created by 123 on 2016/11/25.
 */
public interface ItemsService {
    //商品差寻类被
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws  Exception;
//根据id查询商品信息
    public ItemsCustom findItemsById(int id) throws  Exception;
    //修改商品信息
    public void updateItems(int id, ItemsCustom itemsCustom) throws  Exception;
}
