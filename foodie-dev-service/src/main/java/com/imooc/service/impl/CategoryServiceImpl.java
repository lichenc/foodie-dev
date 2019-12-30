package com.imooc.service.impl;

import com.common.utils.Const;
import com.common.utils.IMOOCJSONResult;
import com.imooc.mapper.CategoryCustomMapper;
import com.imooc.mapper.CategoryMapper;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.service.CategoryService;
import com.imooc.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryCustomMapper categoryCustomMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public IMOOCJSONResult queryAllRootLevelCats(Integer type) {
        //1、创建查询的实例，将是否展示和排序字段作为查询条件
        Example carouselExample = new Example(Category.class);
        //2、创建条件实例 criteria是条件
        Example.Criteria criteria = carouselExample.createCriteria();
        //3、添加条件。property 和 value,YES表示展示
        criteria.andEqualTo("type",type);
        //4、获取到查询的结果
        List<Category> result = categoryMapper.selectByExample(carouselExample);
        if(result == null){
            return IMOOCJSONResult.errorMsg("商品类型为空");
        }
        //将返回中的敏感信息设置为null
        return IMOOCJSONResult.ok(result);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public IMOOCJSONResult querySubCatList(Integer rootCatId) {
        List<CategoryVO> result = categoryCustomMapper.querySubCatList(rootCatId);
        if(result == null){
            return IMOOCJSONResult.errorMsg("分类不存在");
        }
        return IMOOCJSONResult.ok(result);
    }
}
