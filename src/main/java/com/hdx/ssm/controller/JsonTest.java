package com.hdx.ssm.controller;

import com.hdx.ssm.po.ItemsCustom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: lovegod
 * Created by 123 on 2016/11/27.
 */
@Controller
public class JsonTest {
    //请求json，输出相应json
    //RequestBody将请求的商品信息的json传专为ItemsCustom对象
    //ResponseBody将ItemsCustom转为json输出
    @RequestMapping("/requestJson")
    public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom){

        return itemsCustom;
    }
    @RequestMapping("/responseJson")
    public @ResponseBody ItemsCustom responseJson(@RequestBody ItemsCustom itemsCustom){

        return itemsCustom;
    }
}
