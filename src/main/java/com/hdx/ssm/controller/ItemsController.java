package com.hdx.ssm.controller;


import com.hdx.ssm.controller.validation.ValiGroup1;
import com.hdx.ssm.exception.CustomException;
import com.hdx.ssm.po.ItemsCustom;
import com.hdx.ssm.po.ItemsQueryVo;
import com.hdx.ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Author: lovegod
 * Created by 123 on 2016/11/26.
 */
@Controller
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    private ItemsService itemsService;

    //商品分类
    @ModelAttribute("itemsTypes")
    public Map<String,String> getItemsType(){
        Map<String,String> itemsTypes=new HashMap<String, String>();
        itemsTypes.put("101","数码");
        itemsTypes.put("102","母婴");
        return itemsTypes;
    }

    //商品查询
    @RequestMapping("/queryItems")
    public ModelAndView queryItems(ItemsQueryVo itemsQueryVo)throws  Exception{
        //调用service查询数据库，查询商品列表，
        List<ItemsCustom> itemsList=itemsService.findItemsList(itemsQueryVo);
        //返回ModelAndView
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("itemsList",itemsList);
        //指定视图
        modelAndView.setViewName("items/itemsList");
        return  modelAndView;
    }
    //查询商品信息，输出json
    ///itemsView/{id}表示将这个未知的参数传到PathVariable指定的名称当中
    @RequestMapping("/itemsView/{id}")
    public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id)throws  Exception{
        ItemsCustom itemsById = itemsService.findItemsById(id);
        return  itemsById;
    }

    //返回String
    @RequestMapping(value = "editItems",method = {RequestMethod.POST,RequestMethod.GET})
    //RequestParam对制定转入参数绑定
    //required是否必须掺入参数
    //defaultValue 默认转入参数
    public String editItems(Model model, @RequestParam(value="id",required = true,defaultValue = "2") Integer id)throws Exception{
        //传参RequestParam
        ItemsCustom itemsCustom=itemsService.findItemsById(id);
        //判断商品是否为空
        if(itemsCustom==null){
            throw  new CustomException("修改的商品信息不存在");
        }
        //返回ModelandView
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.addObject("itemedit",itemsCustom);
//        modelAndView.setViewName("items/editItems");
        model.addAttribute("itemsCustom",itemsCustom);
        return  "items/editItems";
    }
    //商品修改提交
    //在需要校验的po前需要添加注解Validated，再后边添加BindingResult接受错误信息，配对出现Validated+po+BindingResult
    //@Validated(value = {ValiGroup1.class})指定使用ValiGroup1分组的校验
    //
    @RequestMapping("/editItemsSubmit")
    public String editItemsSubmit(Model model, Integer id ,@ModelAttribute("items") @Validated(value = {ValiGroup1.class}) ItemsCustom itemsCustom, BindingResult bindingResult,
                                  MultipartFile items_pic//接受商品图片
    )throws Exception{
        //获取校验错误信息
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
          //将错误传到页面
            model.addAttribute("allErrors",allErrors);
            //可以直接用model将提交的po回显到界面
//            model.addAttribute("items","itemsCustom");
            //出错重新到商品修改界面
            return  "items/editItems";

        }

        String originalFilename = items_pic.getOriginalFilename();
        //上传图片
        if(items_pic!=null&&originalFilename!=null&&originalFilename.length()>0){
            //存储新图片的屋里路径
            String pic_path="E:\\JAVA\\develop\\upload\\temp\\";

            //新的图片名称
            String newFileName= UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
           //新图片
            File newfile=new File(pic_path+newFileName);
//将内存中的数据写入磁盘
            items_pic.transferTo(newfile);
            //将新的图片名称写道属性中
            itemsCustom.setPic(newFileName);
        }
        itemsService.updateItems(id,itemsCustom);

//        ModelAndView modelAndView=new ModelAndView();
//
//
//        modelAndView.setViewName("success");
//        return  modelAndView;
        //重定向
        //      return  "redirect:queryItems.action";
        //页面转发
        return  "forward:queryItems.form";
    }
    //批量删除商品信息
    @RequestMapping("/deleteItems")
    public  String deleteItems(Integer[] items_id) throws Exception{
        //调用service批量删除数据
        //...

        return  "success";
    }
    //批量修改商品页面，
    //商品查询
    @RequestMapping("/editItemsQuery")
    public ModelAndView editItemsQuery(ItemsQueryVo itemsQueryVo)throws  Exception{
        //调用service查询数据库，查询商品列表，
        List<ItemsCustom> itemsList=itemsService.findItemsList(itemsQueryVo);
        //返回ModelAndView
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("itemsList",itemsList);
        //指定视图
        modelAndView.setViewName("items/editItemsQuery");
        return  modelAndView;
    }


    //批量好散拼修改提交
    //通过ItemsCustom接受批量提交的商品信息，将商品信息提交到ItemsCustom中的List中
    @RequestMapping("/editItemsAllSubmit")
public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo)throws Exception{

    return  "success";
}

}
