package com.imooc.controller;

import com.common.utils.IMOOCJSONResult;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "首页页面接口", tags = "首页页面信息展示的相关接口")
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "查询所有轮播图", notes = "查询所有轮播图", httpMethod = "GET")
    @GetMapping("/carousel")
    public IMOOCJSONResult carousel(){
        //直接将结果返回
        return carouselService.queryAllCarousel();
    }

    @ApiOperation(value = "查询所有大分类", notes = "查询所有大分类", httpMethod = "GET")
    @GetMapping("/cats")
    public IMOOCJSONResult cats(){
        //直接将结果返回
        return categoryService.queryAllRootLevelCats(1);
    }

    @ApiOperation(value = "查询子分类", notes = "查询子分类", httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")//{rootCatId}是路径参数的定义方式
    public IMOOCJSONResult subCat(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId){//需要在参数里面加上 @PathVariable Integer rootCatId

        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg("分类不存在");
        }
        //直接将结果返回
        return categoryService.querySubCatList(rootCatId);
    }

    @ApiOperation(value = "查询最新六个商品接口", notes = "查询最新六个商品接口", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")//{rootCatId}是路径参数的定义方式
    public IMOOCJSONResult sixNewItems(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId){//需要在参数里面加上 @PathVariable Integer rootCatId

        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg("分类不存在");
        }
        //直接将结果返回
        return categoryService.queryNewItemsList(rootCatId);
    }
}
