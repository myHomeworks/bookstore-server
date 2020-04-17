package com.whu.bookstore_server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.whu.bookstore_server.domain.BookDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "BookDetailMapper")
public interface BookDetailMapper extends BaseMapper<BookDetail> {
}
