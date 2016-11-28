package com.hdx.ssm.service.impl;


import com.hdx.ssm.dao.ItemsMapper;
import com.hdx.ssm.dao.ItemsMapperCustom;
import com.hdx.ssm.po.Items;
import com.hdx.ssm.po.ItemsCustom;
import com.hdx.ssm.po.ItemsQueryVo;
import com.hdx.ssm.service.ItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品管理
 * Author: lovegod
 * Created by 123 on 2016/11/25.
 */
@Service
public class ItemsServiceImpl implements ItemsService {
   @Autowired
   private ItemsMapperCustom itemsMapperCustom;
    @Autowired
    private ItemsMapper itemsMapper;

    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
        //通过ItemsMapperCustem查询数据库
        return itemsMapperCustom.findItemsList(itemsQueryVo);
    }

    public ItemsCustom findItemsById(int id) throws Exception {
        Items items=itemsMapper.selectByPrimaryKey(id);
        //中间对商品进行业务处理
        //。。。。
        //返回ItemsCustom
        ItemsCustom itemsCustom=null;
        //将iTems的属性值拷贝到itemsCustom
        if(items!=null){
            itemsCustom=new ItemsCustom();
            BeanUtils.copyProperties(items,itemsCustom);
        }


        return itemsCustom;
    }

    public void updateItems(int id, ItemsCustom itemsCustom) throws Exception {
        //添加业务校验
        //检验id是否为空

        //更新商品信息
        //updateByPrimaryKeyWithBLOBs要求必须窜入id
        itemsCustom.setId(id);
        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
    }
}
