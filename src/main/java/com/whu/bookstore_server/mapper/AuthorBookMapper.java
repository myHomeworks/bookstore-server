package com.whu.bookstore_server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.whu.bookstore_server.domain.Author;
import com.whu.bookstore_server.domain.AuthorBook;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "AuthorBookMapper")
public interface AuthorBookMapper extends BaseMapper<AuthorBook> {
}
