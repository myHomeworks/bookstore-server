package com.whu.bookstore_server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.whu.bookstore_server.domain.ReviewLike;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "ReviewLikeMapper")
public interface ReviewLikeMapper extends BaseMapper<ReviewLike> {
}
