package com.whu.bookstore_server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.whu.bookstore_server.domain.Ad;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "AdMapper")
public interface AdMapper extends BaseMapper<Ad> {
}
