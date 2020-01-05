package com.imooc.mapper;

import com.imooc.vo.CategoryVO;
import com.imooc.vo.ItemsVO;

import java.util.List;

public interface CategoryCustomMapper {

    public List<CategoryVO> querySubCatList(Integer rootCatId);

    public List<ItemsVO> sixNewItemsList(Integer rootCatId);
}