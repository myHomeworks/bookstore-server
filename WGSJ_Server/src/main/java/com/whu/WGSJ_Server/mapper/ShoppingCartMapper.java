package com.whu.WGSJ_Server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.whu.WGSJ_Server.domain.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "ShoppingCartMapper")
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
