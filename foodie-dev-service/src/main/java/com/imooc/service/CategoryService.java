package com.imooc.service;

import com.common.utils.IMOOCJSONResult;

public interface CategoryService {

    /**
     * c查询所有一级分类
     * @param type
     * @return
     */
    public IMOOCJSONResult queryAllRootLevelCats(Integer type);

    /**
     * 根据一级分类id查询子分类信息
     * @param rootCatId
     * @return
     */
    public IMOOCJSONResult querySubCatList(Integer rootCatId);

    /**
     * 根据一级分类id查询最新的六个商品信息
     * @param rootCatId
     * @return
     */
    public IMOOCJSONResult queryNewItemsList(Integer rootCatId);
}
