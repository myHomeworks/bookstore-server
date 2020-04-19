package com.whu.bookstore_server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.whu.bookstore_server.domain.MyOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "MyOrderMapper")
public interface MyOrderMapper extends BaseMapper<MyOrder> {
}
