package com.whu.bookstore_server.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.whu.bookstore_server.domain.Author;
import com.whu.bookstore_server.domain.AuthorBook;
import com.whu.bookstore_server.domain.Book;
import com.whu.bookstore_server.mapper.AuthorMapper;
import com.whu.bookstore_server.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AuthorService")
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public Integer addAuthor(Author author) {
        return authorMapper.insert(author);
    }

    @Override
    public Integer deleteAuthorById(String authorId) {
        return authorMapper.deleteById(authorId);
    }

    @Override
    public Integer updateAuthor(Author author) {
        return authorMapper.updateById(author);
    }

    @Override
    public Author getAuthorById(String authorId) {
        return authorMapper.selectById(authorId);
    }

    @Override
    public Author getAuthorByName(String name) {
        List<Author> authors = authorMapper.selectList(new EntityWrapper<Author>()
                .eq("name", name));
        if (authors.size() == 0)
            return null;
        else
            return authors.get(0);
    }
}
