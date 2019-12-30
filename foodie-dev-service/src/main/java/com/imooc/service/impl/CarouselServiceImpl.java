package com.imooc.service.impl;

import com.common.utils.Const;
import com.common.utils.IMOOCJSONResult;
import com.imooc.mapper.CarouselMapper;
import com.imooc.pojo.Carousel;
import com.imooc.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public IMOOCJSONResult queryAllCarousel() {
        //1、创建查询的实例，将是否展示和排序字段作为查询条件
        Example carouselExample = new Example(Carousel.class);
        //2、创建条件实例 criteria是条件
        Example.Criteria criteria = carouselExample.createCriteria();
        //3、增加排序策略,根据sort顺序查
        carouselExample.orderBy("sort").asc();
        //4、添加条件。property 和 value,YES表示展示
        criteria.andEqualTo("isShow", Const.Show.YES.getCode());
        //5、获取到查询的结果
        List<Carousel> result = carouselMapper.selectByExample(carouselExample);
        if(result == null){
            return IMOOCJSONResult.errorMsg("轮播图为空");
        }
        //将返回中的敏感信息设置为null
        return IMOOCJSONResult.ok(result);
    }

}
