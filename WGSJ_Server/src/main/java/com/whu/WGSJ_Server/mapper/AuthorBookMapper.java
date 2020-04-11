package com.whu.WGSJ_Server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.whu.WGSJ_Server.domain.Author;
import com.whu.WGSJ_Server.domain.AuthorBook;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "AuthorBookMapper")
public interface AuthorBookMapper extends BaseMapper<AuthorBook> {
}
