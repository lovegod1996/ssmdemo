package com.hdx.ssm.controller;


import com.hdx.ssm.po.Items;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 注解开发Handler
 * Author: lovegod
 * Created by 123 on 2016/11/25.
 */
//使用Controller表示他是一个控制器
@Controller
public class ItemsController3 {

    //商品查询列表
    //RequestMapping实现对queruItems方法和url进行映射，一个方法对应一个url
    //一般建议将url和方法写成一样
    @RequestMapping("/queryItems")
    public ModelAndView queryItems() throws  Exception{
        //调用service查找数据库，查询商品列表，使用静态数据模拟
        List<Items> itemsList=new ArrayList<Items>();
        //像list中填充静态数据
        Items items_1 = new Items();
        items_1.setName("联想笔记本");
        items_1.setPrice(6000f);
        items_1.setDetail("ThinkPad T430 联想笔记本电脑！");

        Items items_2 = new Items();
        items_2.setName("苹果手机");
        items_2.setPrice(5000f);
        items_2.setDetail("iphone6苹果手机！");

        itemsList.add(items_1);
        itemsList.add(items_2);

        //返回ModelandView
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("itemsList",itemsList);
        //指定视图
        //下边的路径如果在视图解析器中配置jsp路径前缀和后缀
       // modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
        //上边的路径配置可以不再程序中指定前缀和后缀
        modelAndView.setViewName("items/itemsList");
        return modelAndView;
    }
    //定义其他方法
   // 商品添加

}
