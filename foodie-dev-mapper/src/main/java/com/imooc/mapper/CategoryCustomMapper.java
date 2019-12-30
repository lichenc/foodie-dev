package com.imooc.mapper;

import com.imooc.vo.CategoryVO;

import java.util.List;

public interface CategoryCustomMapper {

    public List<CategoryVO> querySubCatList(Integer rootCatId);

}