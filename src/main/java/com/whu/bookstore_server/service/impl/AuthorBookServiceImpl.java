package com.whu.bookstore_server.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.whu.bookstore_server.domain.Author;
import com.whu.bookstore_server.domain.AuthorBook;
import com.whu.bookstore_server.domain.Book;
import com.whu.bookstore_server.mapper.AuthorBookMapper;
import com.whu.bookstore_server.mapper.AuthorMapper;
import com.whu.bookstore_server.mapper.BookMapper;
import com.whu.bookstore_server.service.AuthorBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("AuthorBookService")
public class AuthorBookServiceImpl implements AuthorBookService {
    @Autowired
    private AuthorBookMapper authorBookMapper;

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Integer addAuthorBook(AuthorBook authorBook) {
        return authorBookMapper.insert(authorBook);
    }

    @Override
    public Integer deleteAuthorBookByBookId(String bookId) {
        return authorBookMapper.delete(new EntityWrapper<AuthorBook>()
                .eq("book_id", bookId));
    }

    @Override
    public Integer deleteAuthorBookByAuthorId(String authorId) {
        return authorBookMapper.delete(new EntityWrapper<AuthorBook>()
                .eq("author_id", authorId));
    }

    @Override
    public List<Author> getAuthorsByBookId(String bookId) {
        List<AuthorBook> authorBooks = authorBookMapper.selectList(new EntityWrapper<AuthorBook>()
                .eq("book_id", bookId));
        List<Author> authors = new ArrayList<>();
        for (AuthorBook authorBook : authorBooks) {
            authors.add(authorMapper.selectById(authorBook.getAuthorId()));
        }
        return authors;
    }

    @Override
    public List<Book> getBooksByAuthorId(String authorId) {
        List<AuthorBook> authorBooks = authorBookMapper.selectList(new EntityWrapper<AuthorBook>()
                .eq("author_id", authorId));
        List<Book> books = new ArrayList<>();
        for (AuthorBook authorBook : authorBooks) {
            books.add(bookMapper.selectById(authorBook.getBookId()));
        }
        return books;
    }


}
